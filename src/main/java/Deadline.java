public class Deadline extends Task {
    private String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    private String getDeadline() {
        return "(by:" + this.deadline + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + this.getDeadline();
    }
}
