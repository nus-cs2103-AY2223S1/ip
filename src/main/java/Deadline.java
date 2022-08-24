public class Deadline extends Task {
    private String deadline;

    public static final String ENCODED_TASK_TYPE = "D";

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return String.format("[D] %s %s (by: %s)", getStatusIcon(), getDescription(), getDeadline());
    }

    /**
     * @return The task-representation of an event written to the file
     */
    @Override
    public String encode() {
        return String.format("%s|%d|%s|%s\n", ENCODED_TASK_TYPE, getIsDone() ? 1 : 0, getDescription(), getDeadline());
    }
}
