package ru.maks.springday.repository;

import org.springframework.stereotype.Repository;
import ru.maks.springday.model.Task;
import ru.maks.springday.model.TaskStatus;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TaskRepository {
    private final Map<Integer, Task> tasks = new LinkedHashMap<>();
    private int nextId = 4;

    public int nextId() {
        return nextId++;
    }

    public Task save(Task task) {
        tasks.put(task.getId(), task); // 5, 6 - hellow - new;
        return task;
    }

    public TaskRepository() {
        tasks.put(1, new Task(1, "Выучить аннотации", TaskStatus.DONE));
        tasks.put(2, new Task(2, "Сделать практику 2 дня", TaskStatus.IN_PROGRESS));
        tasks.put(3, new Task(3, "Задать вопросы ментору", TaskStatus.NEW));
    }

    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    public Task findById(int id) {
        return tasks.get(id);
    }

    public void delete(int id) {
        tasks.remove(id);
    }

    public Task update(Task task) {
        tasks.put(task.getId(), task);
        return task;
    }
}