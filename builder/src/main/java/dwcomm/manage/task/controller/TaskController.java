package dwcomm.manage.task.controller;

import dwcomm.manage.task.dto.CreateTaskItemDto;
import dwcomm.manage.task.dto.ResultsDto;
import dwcomm.manage.task.jpa.TaskItem;
import dwcomm.manage.task.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    // дописать доку swagger
    @Operation(summary = "Get all tasks")
    @GetMapping("/tasks")
    public List<TaskItem> getTasks() {
        return taskService.getAllTaskItems();
    }

    @PostMapping("/task")
    public TaskItem createTask(@RequestBody CreateTaskItemDto createTaskItemDto) {
        return taskService.createTaskItem(createTaskItemDto);
    }

    @GetMapping("/results")
    public ResultsDto getResults() {
        return taskService.getResults();
    }


}
