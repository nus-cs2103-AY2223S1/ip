package task;

public class Deadline extends Task {
    private String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    private String getDeadline() {
        return " (by: " + deadline + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + this.getDeadline();
    }

    @Override
    public String encode() {
        return "D" + super.encode() + ",,," + deadline;
    }
}
