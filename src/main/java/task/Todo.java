package task;

/**
 * Encapsulates a task without any date/time attached to it.
 *
 * @author fannyjian
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
