package duke.task;

import java.time.LocalDate;

import duke.DukeException;
import duke.util.Parser;


/**
 * Task with a deadline.
 */
public class Deadline extends Task {
    private LocalDate dueDate;

    /**
     * Constructor for {@code Deadline}. With deadline {@code LocalDate}.
     *
     * @param description Description of the {@code Deadline} task.
     * @param isDone Completion status of the {@code Deadline} task.
     * @param dueDate {@code LocalDate} of deadline.
     */
    public Deadline(String description, boolean isDone, LocalDate dueDate) {
        super(description, isDone);
        this.dueDate = dueDate;
    }

    /**
     * Constructor for {@code Deadline}. With deadline date string.
     *
     * @param description Description of the {@code Deadline} task.
     * @param isDone Completion status of the {@code Deadline} task.
     * @param dueDate Deadline date string.
     * @throws DukeException Checked exceptions that may occur when parsing date string.
     */
    public Deadline(String description, boolean isDone, String dueDate) throws DukeException {
        this(description, isDone, Parser.parseDate(dueDate));
    }

    /**
     * Constructor for {@code Deadline}. With deadline date string.
     * And assumes completion status is {@code false}.
     *
     * @param description Description of the {@code Deadline} task.
     * @param dueDate Deadline date string.
     * @throws DukeException Checked exceptions that may occur when parsing date string.
     */
    public Deadline(String description, String dueDate) throws DukeException {
        this(description, false, Parser.parseDate(dueDate));
    }

    String getFormattedDateString() {
        return this.dueDate.format(DATE_FORMATTER);
    }

    /**
     * Returns a String representation of the {@code Deadline} task in storage format.
     *
     * @return String representation of the {@code Deadline} task in storage format.
     */
    @Override
    public String getStorageFormat() {
        return String.format("D | %s | %s", super.getStorageFormat(), this.dueDate);
    }

    /**
     * Returns a String representation of {@code Deadline} task in display format.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.getFormattedDateString());
    }
}
