public class Event extends Task {

    // class variables
    protected String event;

    // constructor
    public Event(String description, String event) {
        super(description);
        this.event = event;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.event + ")";
    }
}
