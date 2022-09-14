package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates a Task which has a duration.
 *
 */
public class Event extends Task {
    private LocalDate duration;

    /**
     * Constructor.
     *
     * @param name
     * @param duration
     */
    public Event(String name, String duration) throws DateTimeParseException {
        super(name);
        this.duration = LocalDate.parse(duration);
    }

    /**
     * Returns String representation of the Event object.
     *
     * @return String representation of the Event object
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.duration.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns the duration of the Event object as a String.
     *
     * @return duration of the Event object
     */
    public String getDuration() {
        return this.duration.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public int compareTo(Task task) {
        if (task instanceof ToDo) {
            return -1;
        } else if (task instanceof Deadline) {
            return 1;
        } else {
            Event otherEvent = (Event) task;
            return this.duration.compareTo(otherEvent.duration);
        }
    }
}
