package task;

/**
 * Represents todo tasks in the task list.
 */
public class Todo extends Task {

    /**
     * Constructs an unmarked {@link Todo} object.
     *
     * @param name Name of the task.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Constructs a marked or an unmarked {@link Todo} object.
     *
     * @param name Name of the task.
     * @param isDone The marked status of the task.
     */
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", this.getType(), super.toString());
    }

    @Override
    public String getTime() {
        return "";
    }

    @Override
    public String getType() {
        return "T";
    }
}
