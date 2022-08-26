package duke;

/**
 * Represents a task that has no due date or time
 */
public class Todo extends Task {

    /**
     * Constructor for the Todo class
     * @param description A string that provides information about the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the todo task as a string
     * @return The todo task as a string
     */
    @Override
    public String toString() {
        return ("T | " + super.toString());
    }
}
