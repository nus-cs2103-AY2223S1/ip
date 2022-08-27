package task;

import task.Task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getSaveFormat() {
        return "T" + " | " + (getIsDone() ? 1 : 0) + " | " + getDescription() + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
