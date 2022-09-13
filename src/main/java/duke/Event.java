package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.ImproperEventFormatException;

/**
 * Represents an event.
 * A <code>Event</code> object has a description of task
 * and the allocated time for the event.
 */
public class Event extends Task {

    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter TIME_FORMAT =
            DateTimeFormatter.ofPattern("h:mm a");

    private LocalDate date;
    private LocalTime time;
    private String at;

    /**
     * Event consist of description and allocated time slot.
     *
     * @param description description of task.
     * @param at allocated time slot of task.
     * @throws ImproperEventFormatException if event does not follow the format of
     *                                         event (description) /at YYYY-MM-DD hh:mm
     */
    public Event(String description, String at) throws ImproperEventFormatException {
        super(description);
        this.at = at;
        try {
            String[] arr = at.split(" ");
            this.date = LocalDate.parse(arr[1]);
            this.time = LocalTime.parse(arr[2]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ImproperEventFormatException();
        } catch (DateTimeParseException e) {
            throw new ImproperEventFormatException();
        }
    }

    /**
     * Returns String representation of event
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[E] " + this.getStatusIcon() + " "
                + super.description
                + " (at: "
                + date.format(DATE_FORMAT)
                + ", "
                + time.format(TIME_FORMAT)
                + ")";
    }

    /**
     * Returns String representation in
     * "E|0 or 1|task|at|"
     * where 1 represents the task is marked and 0 otherwise
     *
     * @return String representation.
     */
    @Override
    public String toSaveVersion() {
        if (this.isDone()) {
            return "E|1|"
                    + super.description
                    + "|"
                    + this.at
                    + "\n";
        } else {
            return "E|0|"
                    + super.description
                    + "|"
                    + this.at
                    + "\n";
        }
    }

    /**
     * Returns date and time representation.
     *
     * @return LocalDateTime.
     */
    private LocalDateTime getDateTime() {
        return LocalDateTime.of(this.date, this.time);
    }

    /**
     * Returns 1 if this event's time slot is larger than
     * target's time slot.
     * 0 if both time slot are the same.
     * -1 if time slot is smaller than target's time slot.
     *
     * @param event target event of comparison.
     * @return int.
     */
    public int compareChronologically(Event event) {
        return this.getDateTime().compareTo(event.getDateTime());
    }

    /**
     * Returns 1 if this event's description's is larger than
     * target's description.
     * 0 if both description are the same.
     * -1 if description is smaller than target's description.
     *
     * @param event target event of comparison.
     * @return int.
     */
    public int compareLexicographically(Event event) {
        return this.description.compareTo(event.description);
    }

    /**
     * Returns true if two events share the same
     * description and allocated time slot.
     *
     * @param obj target Object of comparison
     * @return boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event x = (Event) obj;
            if (this.description == null
                    || this.at == null
                    || x.description == null
                    || x.at == null) {
                return false;
            }
            return this.description.equals(x.description)
                    && this.at.equals(x.at);
        }

        return false;
    }
}
