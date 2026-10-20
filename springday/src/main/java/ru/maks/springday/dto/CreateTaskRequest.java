package ru.maks.springday.dto;

public class CreateTaskRequest {

    private String title;

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
}