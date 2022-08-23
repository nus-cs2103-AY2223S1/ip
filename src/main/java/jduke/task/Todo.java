package jduke.task;

import java.time.LocalDate;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    @Override
    protected String getType() {
        return "T";
    }

    @Override
    public boolean isEqualDate(LocalDate date) {
        return false;
    }

    @Override
    public String toStorageFormat() {
        return String.format("T | %d | %s", (this.isCompleted ? 1 : 0), this.description);
    }
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.getType(), (this.isCompleted ? "X" : " "), this.description);
    }
}
