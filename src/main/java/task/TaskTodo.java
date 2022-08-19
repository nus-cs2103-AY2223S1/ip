package task;

/**
 * The {@code TaskTodo} class stores relevant information for a todoTask.
 */
public class TaskTodo extends Task {

    public TaskTodo(String taskName) {
        super(taskName);
    }

    /**
     * Returns string representation of a taskTodo.
     *
     * @return a string representing the taskTodo.
     */
    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
