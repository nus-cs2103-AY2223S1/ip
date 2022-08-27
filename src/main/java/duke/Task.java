package duke;

import java.time.LocalDate;

public abstract class Task {
    private String taskName;
    private boolean completed;

    public Task(String name) {
        this.taskName = name;
        this.completed = false;
    }

    public void complete() {
        this.completed = true;
    }

    public void incomplete() {
        this.completed = false;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean isCompleted() {
        return this.completed;
    }


    public abstract String getTaskType();

    public abstract String getTime();

    public abstract void setTime(String time);

    public abstract void setDate(LocalDate date);

    public abstract String getDateFormat();

    @Override
    public String toString() {
        return (this.completed ? "[X] " : "[ ] ") + this.taskName;
    }
}
