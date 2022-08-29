package duke;

/**
 * Represents a ToDo object that is also a Task.
 * Inherits from the Task class.
 *
 */
public class Todo extends Task {

    private final char type = 'T';

    /** Constructor for a Todo object */
    public Todo(String taskname) {
        super(taskname);
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", this.type, super.toString());
    }

    @Override
    public String toSavedString() {
        return "" + this.type + "#" + super.toSavedString();
    }
}
