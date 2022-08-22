package duke.task;

/**
 * To Do task for Duke application.
 *
 * @author Farrel Dwireswara Salim
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo instance.
     *
     * @param description the description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Gets the string representation of the ToDo.
     *
     * @return the string which represents the current ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Transforms the ToDo to a string that is compatible to Duke's storage.
     *
     * @return the string to be saved in the storage.
     */
    @Override
    public String toStorageRepresentation() {
        return "T|" + super.toStorageRepresentation();
    }
}
