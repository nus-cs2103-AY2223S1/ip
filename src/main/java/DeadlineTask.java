public class DeadlineTask extends Task {

    protected String dateline;

    public DeadlineTask(String description, String dateline) {
        super(description);
        this.dateline = dateline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateline + ")";
    }
}
