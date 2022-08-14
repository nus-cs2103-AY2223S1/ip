public class Event extends Task {

    protected String when;

    public Event(String description, String when) {
        super(description);
        this.when = when;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + when + ")";
    }
}
