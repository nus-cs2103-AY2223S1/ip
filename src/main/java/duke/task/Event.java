package duke.task;

/** A class representing a type of task that is an event to attend, and includes a date when the event occurs. */
public class Event extends Task{

    protected String on;

    public Event(String description, String on) {
        super(description);
        this.on = on;
    }

    @Override
    public String convertToFile() {
        return "event," + this.description + " /at " + this.on;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.on + ")";
    }
}
