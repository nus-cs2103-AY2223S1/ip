package duke;

/**
 * ToDo class representing the user's ToDo list.
 */
public class ToDo extends Task {

    /**
     * Creates a new ToDo object with the specified description.
     * @param description Information about the task associated with this ToDo object.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * String representation of this ToDo object, with information about its completion status.
     * @return A String representation of this ToDo object.
     */
    @Override
    public String toString() {
        return "  [T] [" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Changes the description of this ToDo.
     * @param newDesc The new description of this ToDo.
     */
    public void setDescription(String newDesc) {
        this.description = newDesc;
    }
}
