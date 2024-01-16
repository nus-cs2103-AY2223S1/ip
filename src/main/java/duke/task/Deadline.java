package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.DukeException;

/**
 * Represents tasks that have to be done by a specific deadline.
 *
 * @author ish1506
 */
public class Deadline extends Task {
    private final LocalDate date;

    /**
     * Constructs a <code>Deadline</code>.
     *
     * @param name the name of the deadline.
     * @param date the date of the deadline.
     * @throws DukeException when date is not formatted properly.
     */
    public Deadline(String name, String date) throws DukeException {
        super(name);
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please use this format for the deadline: YYYY-MM-DD");
        }
    }

    /**
     * Constructs a <code>Deadline</code>.
     *
     * @param name   the name of the deadline.
     * @param date   the date of the deadline.
     * @param isDone the status of the deadline.
     */
    public Deadline(String name, String date, boolean isDone) {
        super(name, isDone);
        this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns a <code>Deadline</code> instance from its string representation.
     *
     * @param inputString the string representation of the <code>Deadline</code>.
     * @return the <code>Deadline</code> instance.
     */
    public static Deadline fromString(String inputString) {
        boolean isDone = inputString.charAt(4) == 'X';
        String name = inputString.substring(7, inputString.indexOf("(by"));
        String dateString = inputString.substring(inputString.indexOf("(by: ") + 5, inputString.length() - 1);
        return new Deadline(name, dateString, isDone);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
