package task;

public class Event extends Task {
    private String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.time);
    }

    @Override
    public String toStorageRepresentation() {
        return "E|" + super.toStorageRepresentation() + "|" + this.time;
    }
}
