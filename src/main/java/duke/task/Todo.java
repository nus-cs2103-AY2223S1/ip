package duke.task;

/**
 * Represent a task without a specified date/time.
 */
public class Todo extends Task {

    /**
     * Creates an ToDo instance.
     * @param description Description of the ToDo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of ToDo to be stored.
     * @return A String representation of ToDo to be stored in Storage.
     */
    @Override
    public String toStorageString() {
        String taskString = super.toStorageString();
        return "T" + Task.STORAGE_DELIMITER + taskString;
    }

    /**
     * Returns the string representation of ToDo to be displayed.
     * @return A String representation of ToDo to be displayed.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}




