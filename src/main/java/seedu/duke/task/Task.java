package seedu.duke.task;

import java.time.LocalDate;

public abstract class Task {
    private final String taskName;
    private boolean isDone;

    Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public boolean check() {
        if (isDone) {
            return false;
        } else {
            isDone = true;
            return true;
        }
    }

    public boolean uncheck() {
        if (isDone) {
            isDone = false;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", (isDone ? 'X' : ' '), taskName);
    }

    public String saveFileFormat() {
        return String.format("%d###%s", isDone ? 1 : 0, taskName);
    }

    public boolean isClash(Task task) {
        return false;
    }

    abstract String timeIndex();
}
