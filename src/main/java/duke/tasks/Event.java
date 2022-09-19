package duke.tasks;

import duke.exceptions.DukeInvalidDateException;

/**
 * Encapsulates an Event task in Duke
 */
public class Event extends Task {

    protected String at;
    protected Date date;

    /**
     * Initialiises the Event object
     *
     * @param description The description of the Event task
     * @param at The date of the Event task
     * @throws DukeInvalidDateException if string at does not follow the correct date format
     */
    public Event(String description, String at) throws DukeInvalidDateException {
        super(description);
        this.at = at;
        this.date = new Date(at);
    }

    /**
     * Returns the string representation of the Event object
     *
     * @return the string representation of the Event object
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date + ")";
    }

    /**
     * Returns the string representation of the Event object for storing
     * in the local file
     *
     * @return the string representation of the Event object for local file storing
     */
    @Override
    public String toStringForFile() {
        return super.toStringForFile() + "event " + this.description + " /at " + this.at;
    }

}
