/**
 * This class encapsulates the idea of a deadline.
 */
public class Deadline extends Task {
    private String deadline;

    /**
     * Constructor for a deadline
     * @param description what is the task
     * @param status whether it has been completed
     * @param deadline when is the task due
     */
    public Deadline(String description, boolean status, String deadline) {
        super(description, status);
        this.deadline = deadline;
    }

    @Override
    public String getDescription() {
        String status = super.getStatus() ? "T" : "F";
        return "D | " + status + " | " + super.toString() + " | " + deadline + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() + " " + super.toString() + "(by: " + this.deadline + ")";
    }
}
