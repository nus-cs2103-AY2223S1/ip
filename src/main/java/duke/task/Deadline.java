package duke.task;

import java.time.LocalDate;

import duke.DukeException;
import duke.util.Parser;


/**
 * Task with a deadline.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructor for {@code Deadline}. With deadline {@code LocalDate}.
     *
     * @param description Description of the {@code Deadline} task.
     * @param isDone Completion status of the {@code Deadline} task.
     * @param by {@code LocalDate} of deadline.
     */
    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Constructor for {@code Deadline}. With deadline date string.
     *
     * @param description Description of the {@code Deadline} task.
     * @param isDone Completion status of the {@code Deadline} task.
     * @param by Deadline date string.
     * @throws DukeException Checked exceptions that may occur when parsing date string.
     */
    public Deadline(String description, boolean isDone, String by) throws DukeException {
        this(description, isDone, Parser.parseDate(by));
    }

    /**
     * Constructor for {@code Deadline}. With deadline date string.
     * And assumes completion status is {@code false}.
     *
     * @param description Description of the {@code Deadline} task.
     * @param by Deadline date string.
     * @throws DukeException Checked exceptions that may occur when parsing date string.
     */
    public Deadline(String description, String by) throws DukeException {
        this(description, false, Parser.parseDate(by));
    }

    String getFormattedDateString() {
        return this.by.format(DATE_FORMATTER);
    }

    /**
     * Returns a String representation of the {@code Deadline} task in storage format.
     *
     * @return String representation of the {@code Deadline} task in storage format.
     */
    @Override
    public String getStorageFormat() {
        return "D | " + super.getStorageFormat() + " | " + this.by;
    }

    /**
     * Returns a String representation of {@code Deadline} task in display format.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getFormattedDateString() + ")";
    }
}
