package duke.task;

/**
 * Represents a ToDo Task
 */
public class ToDo extends Task {

    /**
     * Creates a ToDo object
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Creates a ToDo object
     * to be used when loading from task file
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }


    /**
     * Returns a string describing the ToDo object
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


    /**
     * Returns a string describing the ToDo object
     * to be used when saving to task file
     */
    @Override
    public String toStringData() {
        return "T | " + super.toStringData();
    }
}
