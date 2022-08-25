package models;

import models.Task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}