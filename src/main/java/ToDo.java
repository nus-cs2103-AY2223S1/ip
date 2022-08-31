/**
 * Class used to represent a ToDo type task that has no date.
 */
public class ToDo extends Task {
    public ToDo(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    @Override
    public String toSaveFormatString() {
        return String.format("T|%d|%s", isDone ? 1 : 0, taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
