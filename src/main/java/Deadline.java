public class Deadline extends Task {
    String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public static Deadline createDeadline(String input) {
        String deadlineDescription = input.split("/by ")[0];
        String deadline = input.split("/by ")[1];
        return new Deadline(deadlineDescription, deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.deadline + ")";
    }
}
