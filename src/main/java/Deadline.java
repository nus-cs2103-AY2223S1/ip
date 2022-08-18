/**
 * Deadline class inherits from Task
 * Deadline objects are tasks with deadlines
 * @author Nam Minh Quan
 */

public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }
    /**
     * Sets a new deadline for the task
     * @param deadline new deadline for Event
     */
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + this.deadline + ")";
    }
}
