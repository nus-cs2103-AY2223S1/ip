public class Deadline extends Task {
    private String deadline;

    public Deadline(boolean isDone, String text, String deadline) {
        super(isDone, text);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.deadline);
    }
}