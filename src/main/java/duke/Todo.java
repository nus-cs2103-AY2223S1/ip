package duke;
/**
 * A Todo object is a Task object that needs to be completed by user.
 *
 */
public class Todo extends Task {


    /**
     * Creates a new Todo object with a given description.
     *
     * @param description the description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * The task type for a Deadline object is "T".
     *
     * @return "T"
     */
    @Override
    public String getTaskType() {
        return "T";
    }

    /**
     * Returns the String representation of the Todo object.
     *
     * @return String representation of the Todo object
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Formats the details of the Todo object such that the information can be saved and loaded
     * from files.
     *
     * @return The String representation of the Todo object in a format that can be
     * saved to files.
     */
    @Override
    String saveStringToFile() {
        return super.saveStringToFile();
    }
}
