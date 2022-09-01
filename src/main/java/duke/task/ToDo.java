package duke.task;

/**
 * A class representing a todo task.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo task.
     *
     * @param description Description of this todo.
     * @param isDone Boolean indicating whether this task is done.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the string format of this todo task to be saved in the save file.
     *
     * @return String representation of this todo task in the format it is saved in the save file.
     */
    public String toFileFormat() {
        String isDone = this.isDone ? "1" : "0";
        return "T | " + isDone + " | " + this.description;
    }

    /**
     * String representation of this todo.
     *
     * @return String representing this todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
