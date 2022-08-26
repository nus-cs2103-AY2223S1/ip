public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }

    @Override
    public String toWrite() {
        return String.format("D/%s/%s/%s", (isDone ? "1" : "0"), description.trim(), deadline.trim());
    }

}
