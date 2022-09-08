package sally.task;

/**
 * Deadline class to represent new Deadline task
 *
 * @author liviamil
 */

public class Deadline extends Task {
    protected String moreInfo;

    /**
     * Constructor for Deadline class
     *
     * @param description description of Deadline task
     * @param moreInfo due date for Deadline task
     * @param saveTask indicates if the task should be saved to file
     */
    public Deadline(String description, String moreInfo, boolean saveTask) {
        super(description, moreInfo, saveTask);
        this.moreInfo = moreInfo;
        this.taskType = Type.DEADLINE;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.moreInfo + ")";
    }
}
