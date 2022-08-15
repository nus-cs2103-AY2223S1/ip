package duke;

/**
 * Extension class To Do from Task.
 * Doesn't have a date attached to it
 */
public class Todo extends Task {
    private static final String TYPE = "[T]";
    public Todo(String description) {

        super(description);
    }

    @Override
    public String toString() {
        return TYPE + super.toString();
    }
}
