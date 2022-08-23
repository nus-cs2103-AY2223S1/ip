public class Deadline extends Task {
    private static final String TYPE_SYMBOL = "[D]";
    private String deadline;

    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    public Deadline(String task, String deadline, boolean isDone) {
        super(task, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return TYPE_SYMBOL + super.toString() + " (by: " + deadline + ")";
    }

    @Override
    public String toSaveFileString() {
        return TYPE_SYMBOL + " @ " + getStatusIcon() + " @ " + super.getTask() + " @ " + deadline;
    }
}
