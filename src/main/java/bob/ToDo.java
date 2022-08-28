package bob;

/**
 * Represents ToDo object, a task to be done
 */
public class ToDo extends Task {

    /**
     * Constructor for Todo object with description
     *
     * @param description name or details of task to be done
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the save format of the ToDo object
     *
     * @return String representing how ToDo object is saved
     */
    @Override
    public String toSave() { return "T | " + super.toSave(); }

    /**
     * Returns the string representation of the ToDo object
     *
     * @return String representation of ToDo object
     */
    @Override
    public String toString() { return "[T]" + super.toString(); }
}
