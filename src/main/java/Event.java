public class Event extends Task {

    protected String timeline;

    public Event(String description, String timeline) {
        super(description);
        this.timeline = timeline;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + timeline + ")";
    }
}