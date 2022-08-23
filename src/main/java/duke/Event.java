package duke;

public class Event extends Task {
    protected String time;

    public Event(String description, boolean isDone, String time) {
        super(description, isDone);
        this.time = time;
    }

    @Override
    public String getStorageString() {
        return "E | " + (this.isDone ? "1 | " : "0 | ") + this.description + " | " + this.time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.time + ")";
    }
}
