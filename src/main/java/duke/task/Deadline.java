package duke.task;

import duke.exception.DukeException;
import duke.parser.DukeDateTimeFormatter;

/**
 * Encapsulates a deadline task stored by Apollo.
 *
 * @author Kartikeya
 */
public class Deadline extends DukeTask {
    // Time of deadline
    private final String by;
    private final String description;
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
        this.description = description;
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

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formattedBy + ")";
    }
}
