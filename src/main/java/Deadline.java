public class Deadline extends Task {
    private static final String TYPE_SYMBOL = "[D]";
    private String deadline;

    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return TYPE_SYMBOL + super.toString() + " (by: " + deadline + ")";
    }
}
