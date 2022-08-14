public class Event extends Task {

    protected String range;

    public Event(String description, String range) {
        super(description);
        this.range = range;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + range + ")";
    }
}