package duke;

/**
 * Class used to represent a ToDo type task that has no date.
 */
public class ToDo extends Task {
    public static final String TASK_TYPE_CHARACTER = "T";

    public ToDo(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    @Override
    public String toSaveFormatString() {
        return String.format("%s|%d|%s", TASK_TYPE_CHARACTER, isDone ? 1 : 0, taskName);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", TASK_TYPE_CHARACTER, super.toString());
    }
}
