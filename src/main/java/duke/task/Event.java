package duke.task;

import java.util.Objects;

/**
 * Event task containing a description and when the event is happening.
 */
public class Event extends Task {
    protected String eventTime;

    /**
     * Constructs a new Event task.
     *
     * @param description description of the event.
     * @param eventTime the eventTime of the event.
     */
    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    /**
     * Creates an Event task from an encoded string.
     *
     * @param encodedInput the encoded entry with format eventTime|description
     * @param completed the completion status of the Event task
     * @return the Event task
     */
    public static Event decode(String encodedInput, boolean completed) {
        String[] entries = encodedInput.split("\\|", 2);
        Event event = new Event(entries[1], entries[0]);
        event.setDone(completed);
        return event;
    }

    @Override
    public Type getType() {
        return Type.EVENT;
    }

    @Override
    public String encodeData() {
        return String.format("%s|%s", this.eventTime, this.description);
    }

    @Override
    public String getDisplayText() {
        return String.format("%s (at: %s)", description, eventTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Event)) {
            return false;
        }
        Event event = (Event) o;
        return eventTime.compareTo(event.eventTime) == 0 && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventTime, description, isDone);
    }
}
