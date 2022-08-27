/**
 * This class is used to construct an Event task.
 */
package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static duke.DukeConstants.KEY_SEPARATOR;

public class Event extends Task {
    /** Date of event */
    protected LocalDate at;
    /** Time of event */
    private String time;

    /**
     * Constructor for the Event class.
     *
     * @param description Task description.
     * @param at Date of task.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
        this.time = "";
    }

    /**
     * Constructor for the Event class.
     *
     * @param description Task description.
     * @param at Date of task.
     * @param time Time of task.
     */
    public Event(String description, LocalDate at, String time) {
        super(description);
        this.at = at;
        this.time = time;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String formatToSave() {
        int value;
        if(isDone) {
            value = 1;
        } else {
            value = 0;
        }
        return "E" + KEY_SEPARATOR + value + KEY_SEPARATOR + description + KEY_SEPARATOR + at + " " + time;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + this.time + ")";
    }
}
