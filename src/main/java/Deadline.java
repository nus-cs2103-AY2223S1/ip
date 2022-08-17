/**
 * Encapsulates a task that needs to be done before a specific date/time,
 * e.g., submit report by 11/10/2019 5pm
 *
 * @author Emily Ong Hui Qi
 */

public class Deadline extends Task {
    protected String deadline;
    private static final String TASK_TYPE_ICON = "D";

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String getTaskTypeIcon() {
        return Deadline.TASK_TYPE_ICON;
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), this.deadline);
    }
}
