package duke.task;

import duke.DukeException;

public class Deadline extends TimedTask {
    /**
     * Creates a new Deadline. DukeException is thrown if datetime format is not followed.
     * @param description Task description.
     * @param rawDateTime The time and date to complete the task by.
     * @throws DukeException
     */
    public Deadline(String description, String rawDateTime) throws DukeException {
        super(description, rawDateTime);
    }

    /**
     * Outputs as a String to be printed by UI.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), getFormattedTime());
    }

    /**
     * Outputs as a String to be saved by Storage.
     */
    @Override
    public String getSaveFormat() {
        return String.format("D | %s | %s", super.getSaveFormat(), getFormattedTime());
    }
}
