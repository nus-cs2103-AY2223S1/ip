public class Deadline extends Task {
    private final String deadline;

    public Deadline(String task, String deadline) {
        super(task, "deadline");
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + deadline + ")";
    }
}
