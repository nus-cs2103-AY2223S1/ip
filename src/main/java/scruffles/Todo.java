package scruffles;

/**
 * A standard task that has no deadline or timing
 *
 * @author Shamus Tan
 */
public class Todo extends Task {

    /**
     * Constructor for Todo
     *
     * @param taskName the name of the Todo task
     */
    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Constructor for Todo
     *
     * @param taskName the name of the Todo task
     * @param isDone whether the task has been done
     */
    public Todo(String taskName, boolean isDone) {
        super(taskName);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
