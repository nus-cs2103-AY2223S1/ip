package duke.task;

import duke.exception.DukeException;
import duke.parser.DukeDateTimeFormatter;

/**
 * Encapsulates a deadline task stored by Artemis.
 *
 * @author Kartikeya
 */
public class Deadline extends DukeTask {
    // Time of deadline
    private String by;
    private String formattedBy;

    /**
     * Constructor for a Deadline task.
     *
     * @param description description of the deadline
     * @param by          when the deadline is due
     * @throws DukeException if the input is erroneous
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = by;
        formatTime();
    }

    /**
     * Formats time to the specified format of `MMM d, yyyy | h:mma`
     *
     * @throws DukeException if the input is erroneous
     */
    private void formatTime() throws DukeException {
        this.formattedBy = DukeDateTimeFormatter.format(by);
    }

    /**
     * {@inheritDoc}
     */
    public String getStorageString() {
        return "D >> " + (this.isDone() ? "1" : "0") + " >> "
                + this.description + " >> " + this.by;
    }

    /**
     * Updates time of event.
     *
     * @param newBy new time of event
     * @return the string confirming time of deadline has been updated
     * @throws DukeException if the input is erroneous
     */
    public String updateTime(String newBy) throws DukeException {
        this.by = newBy;
        formatTime();
        return "Deadline time updated!";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formattedBy + ")";
    }
}
