package dwcomm.manage.task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcessTasksService {

    private final TaskService taskService;

    @Scheduled(fixedDelay = 5000)
    public void process() {
        taskService.processNewTaskItems();
    }



}
