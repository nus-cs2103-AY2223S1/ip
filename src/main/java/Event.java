public class Event extends Task {

    protected String at;
    protected String id = "[E]";

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return id + super.toString() + " (at: " + at + ")";
    }
}