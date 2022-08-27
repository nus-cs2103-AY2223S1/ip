/**
 * This class is used to construct a deadline task.
 */
package duke;

import java.time.LocalDate;

import static duke.DukeConstants.KEY_SEPARATOR;

public class Deadline extends Task {
    /** Date of task */
    protected LocalDate date;

    /**
     * Constructor for the Deadline task.
     *
     * @param description Task description.
     * @param date Deadline of task.
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String formatToSave() {
        return isDone
                ? "D" + KEY_SEPARATOR + 1 + KEY_SEPARATOR + description + KEY_SEPARATOR + date
                : "D" + KEY_SEPARATOR + 0 + KEY_SEPARATOR + description + KEY_SEPARATOR + date;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
