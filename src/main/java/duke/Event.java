package duke;

/**
 * Encapsulates a task that happens at a specific time.
 */
public class Event extends TimedTask {

    public Event(String description, String time) {
        super(description, time);
    }

    public Event(String description, String time, boolean done) {
        super(description, time, done);
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
