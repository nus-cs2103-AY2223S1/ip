public class Deadline extends Task{
    private static final String TAG = "[D]";
    private final String DEADLINE;

    public Deadline(String description, String deadline) {
        super(description);
        this.DEADLINE = deadline;
    }

    @Override
    public String toString() {
        return TAG + super.toString() + "(by: " + this.DEADLINE + ")";
    }
}
