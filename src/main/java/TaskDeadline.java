public class TaskDeadline extends Task {

    public static final String deadlineMarker = "/by";
    private final String deadline;

    TaskDeadline(String title, String deadline) {
        super(title);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
    }
}
