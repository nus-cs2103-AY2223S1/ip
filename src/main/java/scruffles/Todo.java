package scruffles;

/**
 * A standard task that has no deadline or timing
 *
 * @author Shamus Tan
 */
public class Todo extends Task {

    public Todo(String taskName) {
        super(taskName);
    }

    public Todo(String taskName, boolean isDone) {
        super(taskName);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
