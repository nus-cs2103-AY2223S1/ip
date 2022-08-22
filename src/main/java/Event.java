public class Event extends Task {
    protected String by;

    public Event(String description, String by) {
        super(description);
        this.by = by;
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + by + ")";
    }


    @Override
    public String toFileString() {
        return String.format("E | %s | %s | %s", getStatusIcon(), this.description, by);
    }
}
