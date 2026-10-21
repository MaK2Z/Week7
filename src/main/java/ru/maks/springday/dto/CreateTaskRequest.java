package ru.maks.springday.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import ru.maks.springday.model.TaskPriority;

public class CreateTaskRequest {

    @NotBlank
    @Size(max = 100)
    private String title;

    @NotNull
    private TaskPriority priority;

    // конструктор без аргументов — Jackson создаёт объект через него
    public CreateTaskRequest() {
    }

    public String getTitle() {
        return title;
    }

    // сеттер — Jackson кладёт значение из JSON через него
    public void setTitle(String title) {
        this.title = title;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }
}