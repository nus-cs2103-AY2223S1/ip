public class Deadline extends Task {

    private static final String DEADLINE_REP = "D";

    protected String deadline;

    public Deadline(String content, String deadline) {
        super(content);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[" + DEADLINE_REP + "]" + super.toString() + " (by: " + this.deadline + ")";
    }

    @Override
    public String toStorage() {
        return DEADLINE_REP + super.toStorage() + Task.SEPARATOR + this.deadline;
    }
}
