public class Event extends Task {
    protected final String TAG = "[E]";
    protected String duration;

    public Event(String descriptor, String duration) {
        super(descriptor);
        this.duration = duration;
    }
    @Override
    public String toString() {
        return TAG + super.toString() + "(at: " + this.duration + ")";
    }
}