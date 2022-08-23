package duke.task;

public class Event extends Task {
    private final String duration;

    public Event(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    @Override
    public String storedString() {
        return "E | " + super.storedString() + " | " + duration;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + duration + ")";
    }
}
