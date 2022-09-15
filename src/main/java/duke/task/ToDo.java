package duke.task;

/**
 * Class containing information regarding
 * task of type ToDo.
 *
 * @author Elbert Benedict
 */
public class ToDo extends Task {
    public static final String TYPE_SYMBOL = "[T]";

    /**
     * Constructs a new ToDo Instance.
     *
     * @param task the task description.
     */
    public ToDo(String task) {
        super(task);
    }

    /**
     * Constructs a new ToDo Instance.
     *
     * @param task the task description.
     * @param isDone whether the task has been marked as done.
     */
    public ToDo(String task, boolean isDone, String priority) {
        super(task, isDone, priority);
    }

    /**
     * Returns the string representation of the
     * ToDo instance.
     *
     * @return string representaion of the ToDo instance.
     */
    @Override
    public String toString() {
        return TYPE_SYMBOL + super.toString();
    }

    /**
     * Returns the string representation for the ToDo Instance
     * for the save file.
     *
     * @return the string representation for the ToDo Instance
     *     for the save file.
     */
    @Override
    public String toSaveFileString() {
        return TYPE_SYMBOL + " @ " + getStatusIcon() + " @ " + super.getPriority()
                + " @ " + super.getTask();
    }
}
