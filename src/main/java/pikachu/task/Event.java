package pikachu.task;

/**
 * Represents a task type. A <code>Event</code> object corresponds to
 * a task with certain location.
 */
public class Event extends Task {

    private final String at;

    /**
     * Initialises event with default not done.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Initialises event with isDone indicated.
     */
    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Returns the name of event "E".
     * @return "E" to represent event.
     */
    public String getName() {
        return "E";
    }

    /**
     * Returns the location of event.
     * @return location of the event.
     */
    public String getTiming() {
        return this.at;
    }

    /**
     * Returns the string of the event when writing into task list.
     * @return event written format in task list.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
