package duke;

/**
 * Encapsulates a task that happens at a specific time.
 */
public class Event extends Task {

    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public Event(String description, String time, boolean done) {
        super(description, done);
        this.time = time;
    }

    @Override
    public String toSaveData() {
        return "E|" + (super.isDone ? "1|" : "0|") + this.description + "|" + this.time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
