package events;

public class Deadline extends Task {
    private final String deadline;

    public Deadline(String text, String deadline) {
        super(text);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.deadline);
    }

    @Override
    public String exportString() {
        return String.format("%s%s%s",
                "D",
                super.exportString(),
                this.deadline);
    }
}
