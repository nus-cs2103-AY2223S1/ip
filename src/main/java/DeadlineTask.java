public class DeadlineTask extends Task {
    protected String by;

    DeadlineTask(String action, boolean isDone, String by) {
        super(action, isDone);
        this.by = by;
    }

    DeadlineTask(String action, String by) {
        this(action, false, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
