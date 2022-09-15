package duke.tasks;

/**
 * Encapsulates a Todo task in Duke
 */
public class Todo extends Task {

    /**
     * Initialises the Todo object
     *
     * @param description The description of the Todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the Todo object
     *
     * @return the string representation of the Todo object
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of the Todo object for storing
     * in the local file
     *
     * @return the string representation of the Todo object for local file storing
     */
    @Override
    public String toStringForFile() {
        return super.toStringForFile() + "todo " + this.description;
    }

}