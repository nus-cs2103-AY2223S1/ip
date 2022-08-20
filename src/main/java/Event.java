public class Event extends Task {
    String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    public String toStringData() {
        return "E | " + super.toStringData() + " | " + at;
    }
}
