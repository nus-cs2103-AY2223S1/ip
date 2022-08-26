package duke.task;

import duke.ParsedDateTime;

/**
 * Handles an event.
 */
public class Event extends Task {
    protected ParsedDateTime datetime;

    /**
     * Creates an event.
     * @param description Description of event.
     * @param at Time of event.
     */
    public Event(String description, String at) {
        this(description, at, false);
    }

    /**
     * Creates an event.
     * @param description Description of event.
     * @param at Time of event.
     * @param done If the task is done.
     */
    public Event(String description, String at, boolean done) {
        super(description, done);
        datetime = new ParsedDateTime(at);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), datetime.toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getAsStringArray() {
        String[] data = super.getAsStringArray();
        return new String[]{ "Event", data[1], data[2], datetime.toString() };
    }
}
