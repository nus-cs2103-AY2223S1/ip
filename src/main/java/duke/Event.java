package duke;

public class Event extends Task {
    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getTime() {
        return at;
    }

    public String getStorageString() {
        return "E" + "|" + (isDone ? "1" : "0") + "|" + getDescription() + "|" + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
