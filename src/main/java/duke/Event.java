package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An Event is a Task that also has a timing.
 */
public class Event extends Task {
    private LocalDateTime timing;

    public static final String ENCODED_TASK_TYPE = "E";

    /**
     * Returns a Deadline instance with the given description and the deadline.
     *
     * @param description The Event description.
     * @param timing The timing of the event.
     */
    public Event(String description, LocalDateTime timing) {
        super(description);
        this.timing = timing;
    }

    public String getTiming() {
        DateTimeFormatter dTF = DateTimeFormatter.ofPattern("dd MMM uuuu HH:mm");
        return dTF.format(timing);
    }

    /**
     * Encodes the timing as the same string representation that the user entered.
     * Removes the T to match that representation.
     *
     * @return The encoded string representation of the event timing.
     */
    public String encodeTiming() {
        return timing.toString().replaceAll("T", " ");
    }

    /**
     * Returns the string representation of the Event instance.
     *
     * @return The string representation of the Event instance.
     */
    @Override
    public String toString() {
        return String.format("[E] %s %s (at: %s)", getStatusIcon(), getDescription(),getTiming());
    }

    /**
     * Returns an encoded string representation of the Deadline instance to be written to the file.
     *
     * @return The string representation of the Event instance written to the file
     */
    @Override
    public String encode() {
        return String.format("%s|%d|%s|%s\n", ENCODED_TASK_TYPE, getIsDone() ? 1 : 0, getDescription(), encodeTiming());
    }
}
