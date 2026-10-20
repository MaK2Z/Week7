package ru.maks.springday.сontroller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.maks.springday.dto.CreateTaskRequest;
import ru.maks.springday.dto.TaskResponse;
import ru.maks.springday.model.TaskStatus;
import ru.maks.springday.service.TaskService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskResponse> getAll(@RequestParam(required = false) TaskStatus status) {
        return taskService.getAll(status);
    }

    @GetMapping("/{id}")
    public TaskResponse getById(@PathVariable int id) {
        return taskService.getById(id);
    }

    @PostMapping
    public ResponseEntity<TaskResponse> create(@RequestBody CreateTaskRequest request) {
        TaskResponse created = taskService.create(request);
        URI location = URI.create("/api/tasks/" + created.getId());
        return ResponseEntity.created(location).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public TaskResponse changeStatus(@PathVariable int id, @RequestParam TaskStatus status) {
        return taskService.changeStatus(id, status);
    }
}