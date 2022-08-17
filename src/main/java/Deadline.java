/*
This class encapsulates the idea of a deadline
 */
public class Deadline extends Task {
    private String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() + " " + super.toString() + "(by: " + this.deadline + ")";
    }
}
