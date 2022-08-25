package models;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    public String formatForSave() {
        return "E | " + super.formatForSave() + " | " + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: " + at + ")\n";
    }
}
