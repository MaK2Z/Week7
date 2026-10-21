package ru.maks.springday.dto;

import jakarta.validation.constraints.NotNull;
import ru.maks.springday.model.TaskStatus;

public class UpdateTaskStatusRequest {

    @NotNull
    private TaskStatus status;

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}