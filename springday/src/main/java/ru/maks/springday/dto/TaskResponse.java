package ru.maks.springday.dto;

import ru.maks.springday.model.TaskStatus;

public class TaskResponse {

    private final int id;
    private final String title;
    private final TaskStatus status;

    public TaskResponse(int id, String title, TaskStatus status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public TaskStatus getStatus() {
        return status;
    }
}