public class Deadline extends Task {
    private final DeadlineDateTime deadline;

    public Deadline(String taskItem, DeadlineDateTime deadline) {
        super(taskItem);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String deadlineDisplay = String.format(" (by: %s)", this.deadline);
        return "[D]" + super.toString() + deadlineDisplay;
    }
}
