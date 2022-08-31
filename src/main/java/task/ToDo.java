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
    public String encode() {
        return "T" + Task.ENCODING_SEPARATOR + super.encode();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}