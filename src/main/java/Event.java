public class Event extends Task {
    private static final String TAG = "[E]";
    private final String RUNTIME;

    public Event(String description, String runtime) {
        super(description);
        this.RUNTIME = runtime;
    }

    @Override
    public String toString() {
        return TAG + super.toString() + "(at: " + this.RUNTIME + ")";
    }
}
