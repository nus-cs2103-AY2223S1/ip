package skyler;

import java.time.LocalDateTime;

/**
 * Represents a task without any date or time attached to it
 */
public class Todo extends Task {
    /**
     * Creates a todo object
     *
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
        assert this instanceof Task : "Todo should be a subtype of Task";
    }

    @Override
    public void changeDateTime(LocalDateTime dateTime) {
        // does nothing since Todo does not have an associated date or time
    }

    @Override
    public String toStringUnformatted() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
