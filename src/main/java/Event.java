public class Deadline extends Task {
    private final String deadline;

    public Deadline(String input, String deadline) {
        super(input);
        this.deadline = deadline;
    }

    public Deadline(String input, boolean done, String deadline) {
        super(input, done);
        this.deadline = deadline;
    }
}
