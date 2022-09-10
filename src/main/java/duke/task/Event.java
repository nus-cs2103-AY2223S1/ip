package duke.task;

import duke.exception.DukeException;
import duke.parser.DukeDateTimeFormatter;

/**
 * Encapsulates an event task stored by Artemis.
 *
 * @author Kartikeya
 */
public class Event extends DukeTask {
    // Time of event
    private String at;
    private String formattedAt;

    /**
     * Constructor for an Event task.
     *
     * @param description description of the event
     * @param at          when the event will occur
     * @throws DukeException if the input is erroneous
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        this.at = at;
        formatTime();
    }

    /**
     * Formats time to the specified format of `MMM d, yyyy | h:mma`
     *
     * @throws DukeException if the input is erroneous
     */
    private void formatTime() throws DukeException {
        this.formattedAt = DukeDateTimeFormatter.format(at);
    }

    /**
     * {@inheritDoc}
     */
    public String getStorageString() {
        return "E >> " + (this.isDone() ? "1" : "0") + " >> "
                + this.description + " >> " + this.at;
    }

    /**
     * Updates time of event.
     *
     * @param newAt new time of event
     * @return the string confirming time of event has been updated
     * @throws DukeException if the input is erroneous
     */
    @Override
    public String updateTime(String newAt) throws DukeException {
        this.at = newAt;
        formatTime();
        return "Event time updated!";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formattedAt + ")";
    }
}
