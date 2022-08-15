public class DeadlineTask extends Task {

    private final String deadline;

    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + getStatusIcon() + description + "(by: " + deadline + ")";
    }
}
