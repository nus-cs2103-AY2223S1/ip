/**
 * Represents a time-sensitive task that has a deadline.
 */
public class DeadlineTask extends Task{

    /** The deadline for the task. */
    private String deadline = "";

    /**
     * Constructor for an deadline task.
     * @param description A description of the deadline
     * @param deadline The time at which the task will expire
     */
    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }


    /**
     * Returns tthe string representation of the deadline.
     * @return String representation of the deadline
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
    }
}
