package duke.task;

import duke.exception.DukeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * This class inherits from the abstract Task class
 * and encapsulates the logic of an Event task.
 */
public class Event extends Task {
    /* Duration field */
    private LocalDateTime duration;
    /**
     * Constructor for the Event Task.
     * @param description description of the task.
     */
    public Event(String description, LocalDateTime duration) {
        super(description);
        this.duration = duration;
    }

    /**
     * Factory Method for the construction of a Event for user input.
     *
     * @param in user's input.
     * @return Event with the relevant input fields.
     * @throws DukeException if no task or incorrect formatting is given.
     */
    public static Event createEvent(String in) throws DukeException {
        String[] temp = in.split(" */at* ");
        if (temp.length != 2) {
            throw new DukeException("-Event- Please follow the format of ~description~ /at dd-MM-yyyy HHmm!\n");
        }
        String description = temp[0];
        String duration = temp[1];
        LocalDateTime event;
        try {
            event = LocalDateTime.parse(duration, INPUT_DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new DukeException("-Event- Your date needs to be in dd-MM-yyyy HHmm format!\n");
        }

        return new Event(description, event);
    }

    /**
     * Override toString method for the Event Task.
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() +" (at: " + duration.format(OUTPUT_DATE_FORMAT) + ")";
    }

    /**
     * Override save format method from Task class.
     *
     * @return formatted String for the Event task.
     */
    @Override
    public String saveFormat() {
        return String.format("E | %s | %s", super.saveFormat(), this.duration.format(OUTPUT_DATE_FORMAT));
    }
}
