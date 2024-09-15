package duke.task;

import duke.DukeException;

/**
 * Event class
 */
public class Event extends TimedTask {
    /**
     * Creates a new Event. DukeException is thrown if datetime format is not followed.
     * @param description Task description.
     * @param rawDateTime The date and time that the event will be held at.
     * @throws DukeException
     */
    public Event(String description, String rawDateTime) throws DukeException {
        super(description, rawDateTime);
    }

    /**
     * Outputs as a String to be printed by UI.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), getFormattedTime());
    }

    /**
     * Outputs as a String to be saved by Storage.
     */
    @Override
    public String getSaveFormat() {
        return String.format("E | %s | %s", super.getSaveFormat(), getFormattedTime());
    }
}
