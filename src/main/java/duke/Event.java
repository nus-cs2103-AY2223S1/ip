package duke;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;

/**
 * The Event class encapsulates an Event task.
 */
public class Event extends Task {
    /** A specific start and end to the task */
    protected String at;
    /** The event duration to be stored in the Event object */
    protected LocalDateTime event;

    /**
     * Instantiates an Event object.
     *
     * @param description Description of the task.
     * @param at A specific start and end to the task.
     * @throws DukeException If at given is in an invalid format.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        this.at = at;
        try {
            this.event = LocalDateTime.parse(at.substring(1));
        } catch (DateTimeParseException e) {
            throw new DukeException("Please input the deadline in yyyy-mm-ddTHours:Minutes:Seconds format. "
                    + "E.g 2019-10-15T10:15:00");
        }
    }

    /**
     * Instantiates an Event object.
     *
     * @param description Description of the task.
     * @param isDone Flag to indicate if a task is done or not.
     * @param at A specific start and end to the task.
     * @throws DukeException If at given is in an invalid format.
     */
    public Event(String description, boolean isDone, String at) throws DukeException {
        super(description, isDone);
        this.at = at;
        try {
            this.event = LocalDateTime.parse(at.substring(1));
        } catch (DateTimeParseException e) {
            throw new DukeException("Please input the deadline in yyyy-mm-ddTHours:Minutes:Seconds format. "
                    + "E.g 2019-10-15T10:15:00");

        }
    }

    /**
     * Returns the event start and end date/time in a valid format.
     *
     * @return event start and end date/time in valid format.
     */
    private String printTime() {
        String s = this.event.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm:ss a"));
        return s;
    }

    @Override
    public String fileStatus() {
        return "E | " + super.fileStatus() + "|" + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: "
                + printTime() + ")";
    }
}

