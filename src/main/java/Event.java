public class Event extends Task {

    protected String by;

    public Event(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + by + ")";
    }
}
