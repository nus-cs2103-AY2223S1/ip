public class Deadline extends Task {
    protected final static String TAG = "[D]";
    protected String due;

    public Deadline(String descriptor, String due) {
        super(descriptor);
        this.due = due;
    }
    @Override
    public String toString() {
        return TAG + super.toString() + " (by: " + this.due + ")";
    }
}
