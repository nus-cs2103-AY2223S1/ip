package duke.task;

/**
 * A concrete class that represents a to-do task and implements its parent abstract class.
 */
public class TodoTask extends Task {

    private static final String LABEL = "T";

    /**
     * The standard constructor.
     */
    public TodoTask(String taskTitle) {
        super(taskTitle, TaskType.TODO);
    }

    /**
     * Returns a string representation of the task, to be displayed on the screen.
     *
     * @return A string representation.
     */
    @Override
    public String toString() {
        return super.getStringRepresentation(LABEL, super.taskTitle);
    }

    /**
     * Returns a string representation for file saving.
     *
     * @return A string that is suitable to be saved as plain text.
     */
    @Override
    public String getFileRepresentation() {
        return super.getFileRepresentation(LABEL);
    }
}
