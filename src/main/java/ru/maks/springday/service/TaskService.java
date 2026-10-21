package ru.maks.springday.service;

import org.springframework.stereotype.Service;
import ru.maks.springday.dto.CreateTaskRequest;
import ru.maks.springday.dto.TaskResponse;
import ru.maks.springday.exception.StatusTransitionException;
import ru.maks.springday.exception.TaskNotFoundException;
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
        Task task = taskRepository.findById(id);
        if (task == null) {
            throw new TaskNotFoundException(id);
        }
        return toResponse(task);
    }

    public TaskResponse create(CreateTaskRequest request) {
        int id = taskRepository.nextId();
        Task task = new Task(id, request.getTitle(), TaskStatus.NEW);
        task.setPriority(request.getPriority());
        return toResponse(taskRepository.save(task));
    }

    public void delete(int id) {
        if (taskRepository.findById(id) == null) {
            throw new TaskNotFoundException(id);
        }
        taskRepository.delete(id);
    }


    public TaskResponse changeStatus(int id, TaskStatus status) {
        Task task = taskRepository.findById(id);
        if (task == null) {
            throw new TaskNotFoundException(id);
        }
        TaskStatus current = task.getStatus();
        boolean allowed = (current == TaskStatus.NEW && status == TaskStatus.IN_PROGRESS)
                || (current == TaskStatus.IN_PROGRESS && status == TaskStatus.DONE)
                || (current == status);
        if (!allowed) {
            throw new StatusTransitionException(
                    "Нельзя перевести задачу из статуса " + current + " в статус " + status);
        }
        task.setStatus(status);
        return toResponse(taskRepository.update(task));
    }

}