package dwcomm.manage.task.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import dwcomm.generator.NumberGenerator;
import dwcomm.manage.task.TaskState;
import dwcomm.manage.task.TaskType;
import dwcomm.manage.task.dto.CreateTaskItemDto;
import dwcomm.manage.task.dto.ResultsDto;
import dwcomm.manage.task.jpa.TaskItem;
import dwcomm.manage.task.jpa.TaskItemRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Service
@Slf4j
public class TaskService {

    private final TaskItemRepository taskItemRepository;
    private final NumberGenerator numberGenerator;
    private final List<Long> resultLongs;
    private final List<List<Long>> resultListOfLongs;

    private final KafkaTemplate<Object, Object> kafkaTemplate;

    @Value("${kafka.topics.long-topic}")
    private String longTopic;

    @Value("${kafka.topics.list-long-topic}")
    private String listLongTopic;

    @EventListener(ApplicationReadyEvent.class)
    public void createAndSaveTaskItems() {
//        List<TaskItem> taskItems = new ArrayList<>();
//
//        taskItems.add(new TaskItem(null, TaskType.GENERATE_LONGS, 10, 0, TaskState.DONE, 5));
//        taskItems.add(new TaskItem(null, TaskType.GENERATE_LISTS_OF_LONG, 20, 0, TaskState.DONE, 5));
//        taskItems.add(new TaskItem(null, TaskType.GENERATE_LISTS_OF_LONG, 30, 0, TaskState.DONE, 5));
//
//        taskItemRepository.saveAll(taskItems);
    }

    public List<TaskItem> getAllTaskItems() {
        return taskItemRepository.findAll();
    }

    public ResultsDto getResults() {
        return new ResultsDto(resultLongs.size(), resultListOfLongs.size(), null, null);
    }

    public void processNewTaskItems() {
        // реализовать блокировку для одного потока
        List<TaskItem> allByTaskState = taskItemRepository.findAllByTaskState(TaskState.NEW);

        allByTaskState.forEach(taskItem -> {
            taskItem.setTaskState(TaskState.PROCESSING);
            taskItemRepository.save(taskItem);

            CompletableFuture.runAsync(processTaskItem(taskItem));
        });
    }

    public Runnable processTaskItem(TaskItem taskItem) {
        return () -> {
            try {
                if (taskItem.getType() == TaskType.GENERATE_LONGS) {
                    for (int i = 0; i < taskItem.getCount(); i++) {
                        Long value = numberGenerator.generate();
                        kafkaTemplate.send(longTopic, value);
                    }
                }

                if (taskItem.getType() == TaskType.GENERATE_LISTS_OF_LONG) {
                    for (int i = 0; i < taskItem.getCount(); i++) {
                        List<Long> longList = numberGenerator.generate(taskItem.getCountInList());
                        kafkaTemplate.send(listLongTopic, longList);
                    }
                }

                taskItem.setTaskState(TaskState.DONE);
                taskItemRepository.save(taskItem);
            } catch (Exception e) {
                log.error("processTaskItem", e);
            }
        };
    }

    public TaskItem createTaskItem(CreateTaskItemDto createTaskItemDto) {
        TaskItem taskItem = new TaskItem(
                null,
                createTaskItemDto.getType(),
                createTaskItemDto.getCount(),
                0,
                TaskState.NEW,
                createTaskItemDto.getCountInList()
        );
        return taskItemRepository.save(taskItem);
    }
}
