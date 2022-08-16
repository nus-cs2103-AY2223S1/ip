public class Deadline extends Task {
    private String deadline;

    public Deadline(String text, String deadline) {
        super(text);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.deadline);
    }
}
