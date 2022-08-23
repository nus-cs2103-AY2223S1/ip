package duke;

public class ToDo extends Task {
    private static final String TYPE_SYMBOL = "[T]";

    public ToDo(String task) {
        super(task);
    }

    public ToDo(String task, boolean isDone) {
        super(task, isDone);
    }

    @Override
    public String toString() {
        return TYPE_SYMBOL + super.toString();
    }

    @Override
    public String toSaveFileString() {
        return TYPE_SYMBOL + " @ " + getStatusIcon() + " @ " + super.getTask();
    }
}
