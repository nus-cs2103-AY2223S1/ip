package duke.model;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toStorage() {
        return "E | " + (this.isDone ? 1 : 0) + " | " + description + " | " + this.at + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
