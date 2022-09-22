package ado.task;
/**
 * Represents a todo task with description.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStringForStorage() {
        return "T|" + super.toStringForStorage();
    }
}
