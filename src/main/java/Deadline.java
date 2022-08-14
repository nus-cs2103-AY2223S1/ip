/**
 * A Deadline object is a Task object that has an associated deadline by which the task should be
 * completed.
 */
public class Deadline extends Task {

    private final String deadline;

    /**
     * Creates a new Deadline object with a given description, whether it has been done and the
     * corresponding deadline.
     *
     * @param description the description of the task
     * @param isDone      whether the task is marked as done
     * @param deadline    the date/time by which the task should be completed
     */
    public Deadline(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * The task type code for a Deadline object is "D". Hence, this method returns "D".
     *
     * @return "D"
     */
    @Override
    public String getTaskTypeCode() {
        return "D";
    }
}
