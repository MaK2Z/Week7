package ru.maks.springday.service;

import org.springframework.stereotype.Service;
import ru.maks.springday.dto.CreateTaskRequest;
import ru.maks.springday.dto.TaskResponse;
import ru.maks.springday.model.Task;
import ru.maks.springday.model.TaskStatus;
import ru.maks.springday.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    private TaskResponse toResponse(Task task) {
        return new TaskResponse(task.getId(), task.getTitle(), task.getStatus());
    }

    public List<TaskResponse> getAll(TaskStatus status) {
        List<Task> all = taskRepository.findAll();
        List<TaskResponse> result = new ArrayList<>();
        for (Task task : all) {
            if (status == null || task.getStatus() == status) {
                result.add(toResponse(task));
            }
        }
        return result;
    }

    public TaskResponse getById(int id) {
        return toResponse(taskRepository.findById(id));
    }

    public TaskResponse create(CreateTaskRequest request) {
        int id = taskRepository.nextId();
        Task task = new Task(id, request.getTitle(), TaskStatus.NEW);
        return toResponse(taskRepository.save(task));
    }

    public void delete(int id) {
        taskRepository.delete(id);
    }


    public TaskResponse changeStatus(int id, TaskStatus status) {
        Task task = taskRepository.findById(id);
        task.setStatus(status);
        return toResponse(taskRepository.update(task));
    }

}