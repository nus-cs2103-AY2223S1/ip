package scottie.tasks;

import java.time.temporal.TemporalAccessor;

import scottie.common.DateTimeUtil;

/**
 * Encapsulates a task which is an event that takes place at a date and time.
 */
public class Event extends Task {
    private final TemporalAccessor dateTime;

    /**
     * Constructs an Event with the given description and dateTime.
     * The Event defaults to being not done.
     *
     * @param description The description of this Event.
     * @param dateTime The date and time of this Event.
     */
    public Event(String description, TemporalAccessor dateTime) {
        this(description, false, dateTime);
    }

    /**
     * Constructs an Event with the given description, isDone status and dateTime.
     *
     * @param description The description of this Event.
     * @param isDone Whether this Event is done.
     * @param dateTime The date and time of this Event.
     */
    private Event(String description, boolean isDone, TemporalAccessor dateTime) {
        super(description, isDone);
        this.dateTime = dateTime;
    }

    /**
     * Returns an Event constructed based on the data in the provided string.
     *
     * @param encodedString The string containing the data for the Event.
     * @return The constructed Event.
     * @throws InvalidTaskDataException If the string is not formatted correctly.
     */
    static Event fromEncodedString(String encodedString) throws InvalidTaskDataException {
        String[] splitTaskData = encodedString.split("\\|");
        if (splitTaskData.length < 4) {
            throw new InvalidTaskDataException("The data for this event is not formatted correctly.");
        }
        String description = splitTaskData[2];
        boolean isDone = splitTaskData[1].equals("1");
        TemporalAccessor dateTime = DateTimeUtil.parseCompactDateTime(splitTaskData[3]);
        return new Event(description, isDone, dateTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toEncodedString() {
        return String.format("E|%s|%s", super.toEncodedString(),
                DateTimeUtil.formatCompactDateTime(this.dateTime));
    }

    @Override
    public String toString() {
        return String.format("(E) %s (at: %s)", super.toString(),
                DateTimeUtil.formatPrettyDateTime(this.dateTime));
    }
}
