public class Event extends Task {
    protected String range;

    public Event(String description, String range, boolean isDone) {
        super(description, isDone);
        this.range = range;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + range + ")";
    }
}