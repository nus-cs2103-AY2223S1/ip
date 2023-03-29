package duke.tasks;

import duke.exceptions.DukeInvalidDateException;

/**
 * Encapsulates a Deadline task in Duke
 */
public class Deadline extends Task {

    protected String by;
    protected Date date;

    /**
     * Initialises the Deadline object
     *
     * @param description The description of the task
     * @param by The deadline of the task
     * @throws DukeInvalidDateException if the string by does not follow the correct date format
     */
    public Deadline(String description, String by) throws DukeInvalidDateException {
        super(description);
        this.by = by;
        this.date = new Date(by);
    }

    /**
     * Returns the string representation of the Deadline object
     *
     * @return the string representation of the Deadline object
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + ")";
    }

    /**
     * Returns the string representation of the Deadline object for storing
     * in the local file
     *
     * @return the string representation of the Deadline object for local file storing
     */
    @Override
    public String toStringForFile() {
        return super.toStringForFile() + "deadline " + this.description + " /by " + this.by;
    }
}
