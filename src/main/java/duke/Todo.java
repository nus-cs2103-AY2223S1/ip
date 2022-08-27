package duke;

public class Todo extends Task {

    public static final String ENCODED_TASK_TYPE = "T";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T] %s %s", getStatusIcon(), getDescription());
    }

    /**
     * Returns the task-representation of an event written to the file.
     *
     * @return The task-representation of an event written to the file.
     */
    @Override
    public String encode() {
        return String.format("%s|%d|%s\n", ENCODED_TASK_TYPE, getIsDone() ? 1 : 0, getDescription());
    }
}
