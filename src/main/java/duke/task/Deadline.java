package duke.task;

import java.time.format.DateTimeFormatter;

import duke.exception.DukeException;

/** This class encapsulates a Deadline task that needs to be completed by a date */
public class Deadline extends Task {

    /** Deadline date of the task */
    private String date;

    /**
     * Initialises Deadline object.
     *
     * @param description Task Description.
     * @param by Date.
     * @param dateIsFormatted True if date has been formatted.
     * @throws DukeException
     */
    public Deadline(String description, String by, boolean dateIsFormatted) throws DukeException {
        super(description);
        if (dateIsFormatted == true) {
            this.date = by;
        } else {
            this.date = getLocalDateTime(by,
                    "deadline", "/by").format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm a"));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDate() {
        return this.date;
    }

    /**
     * Returns String representation of the task with deadline.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }

}
