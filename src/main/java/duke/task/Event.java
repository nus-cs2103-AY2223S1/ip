package duke.task;

/**
 * Represents tasks that have a start and end time.
 *
 * @author ish1506
 */
public class Event extends Task {
    private final String duration;

    /**
     * Constructs an <code>Event</code>.
     * Is incomplete by default.
     *
     * @param name     the name of the event.
     * @param duration the duration of the event.
     */
    public Event(String name, String duration) {
        super(name);
        this.duration = duration;
    }

    /**
     * Constructs an <code>Event</code>.
     *
     * @param name     the name of the event.
     * @param duration the duration of the event.
     * @param isDone   the status of the event.
     */
    public Event(String name, String duration, boolean isDone) {
        super(name, isDone);
        this.duration = duration;
    }

    /**
     * Returns an <code>Event</code> instance from its string representation.
     *
     * @param inputString the string representation of the <code>Task</code>.
     * @return the <code>Task</code> instance.
     */
    public static Event fromString(String inputString) {
        boolean isDone = inputString.charAt(4) == 'X';
        String name = inputString.substring(7, inputString.indexOf("(at"));
        String duration = inputString.substring(inputString.indexOf("(at: ") + 5, inputString.length() - 1);
        return new Event(name, duration, isDone);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.duration + ")";
    }
}
