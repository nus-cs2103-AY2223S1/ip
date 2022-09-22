package duke.tasks;

/**
 * Tasks without any date/time attached to it
 */
public class ToDoTask extends Task {

    /**
     * Constructor for TodoTask with description and isDone initialised to false
     *
     * @param description Description of task details
     */
    public ToDoTask(String description) {
        super(description);
    }

    /**
     * Constructor for TodoTask with both description and isDone initialised
     *
     * @param description Task description
     * @param isDone      Whether task has been completed
     */
    public ToDoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns string representation of TodoTask
     *
     * @return String representation of TodoTask
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns string representation of TodoTask in Storage
     *
     * @return String representation of TodoTask in Storage
     */
    @Override
    public String toSaveString() {
        return "T " + super.toSaveString();
    }
}
