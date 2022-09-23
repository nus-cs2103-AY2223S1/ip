package nyanduke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import nyanduke.NyanDukeException;
import nyanduke.Ui;

/**
 * The Deadline class represents a task
 * that needs to be done by a specified date/time.
 */
public class Deadline extends Task {
    /** A string describing by when the task must be completed. */
    private String by;
    /** The date by which the task must be completed. */
    private LocalDate date;

    /**
     * Constructs a new Deadline task with a specified
     * description and time.
     *
     * @param description A string specifying the description of the task.
     * @param by The specified deadline by which the task must be completed.
     * @throws NyanDukeException when the description or by parameter is an empty String.
     */
    public Deadline(String description, String by) throws NyanDukeException {
        super(description);
        String emptyString = "";
        if (description.equals(emptyString)) {
            throw new NyanDukeException(Ui.ERROR_DEADLINE_DESCRIPTION);
        }
        if (by.equals(emptyString)) {
            throw new NyanDukeException(Ui.ERROR_DEADLINE_BY);
        }

        try {
            LocalDateTime dateTime;
            dateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            date = dateTime.toLocalDate();
            this.by = dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h.mma"));
        } catch (DateTimeParseException e1) {
            try {
                String[] strings = by.split(" ");
                date = LocalDate.parse(strings[0]);
                this.by = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                if (strings.length > 1) {
                    this.by += ", " + by.substring(by.indexOf(" ") + 1);
                }
            } catch (DateTimeParseException e2) {
                this.by = by;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns the representation of a Deadline task when stored in a data file on the hard disk.
     *
     * @return A string representing the Deadline task as it is stored on a data file on the hard disk.
     */
    @Override
    public String toData() {
        return "D | " + super.toData() + " | " + by + "\n";
    }

    /**
     * Checks if the exact date by which a Deadline task should be done
     * is a specified date.
     *
     * @param date The specified date to check.
     * @return true if the specified date is the exact date by which
     *     the Deadline task must be done.
     */
    @Override
    public boolean isOnDate(LocalDate date) {
        assert date != null : "Deadline::isOnDate invoked with null argument.";
        String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return by.startsWith(formattedDate);
    }
}
