public class Deadline extends Task {
    /**
     * The deadline of Deadline
     */
    private String deadline;

    /**
     * Constructor for Deadline
     * @param title The title of Deadline
     * @param isDone The isDone status of the Task
     * @param deadline The deadline of Deadline
     */
    public Deadline(String title, boolean isDone, String deadline) {
        super(title, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
    }
}
