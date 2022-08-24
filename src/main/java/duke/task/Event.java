package duke.task;

import java.time.LocalDate;

import duke.DukeException;
import duke.util.Parser;

/**
 * Event task.
 */
public class Event extends Task {
    private LocalDate at;

    /**
     * Constructor for {@code Event}. With event time {@code LocalDate}.
     *
     * @param description Description of the {@code Event} task.
     * @param isDone Completion status of the {@code Event} task.
     * @param at {@code LocalDate} of event.
     */
    public Event(String description, boolean isDone, LocalDate at) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Constructor for {@code Event}. With event date string.
     *
     * @param description Description of the {@code Event} task.
     * @param isDone Completion status of the {@code Event} task.
     * @param at Event date string.
     * @throws DukeException Checked exceptions that may occur when parsing date string.
     */
    public Event(String description, boolean isDone, String at) throws DukeException {
        this(description, isDone, Parser.parseDate(at));
    }

    /**
     * Constructor for {@code Event}. With event date string.
     * And assumes completion status is {@code false}.
     *
     * @param description Description of the {@code Event} task.
     * @param at Event date string.
     * @throws DukeException Checked exceptions that may occur when parsing date string.
     */
    public Event(String description, String at) throws DukeException {
        this(description, false, Parser.parseDate(at));
    }

    String getFormattedDateString() {
        return this.at.format(DATE_FORMATTER);
    }

    /**
     * Returns a String representation of the {@code Event} task in storage format.
     *
     * @return String representation of the {@code Event} task in storage format.
     */
    @Override
    public String getStorageFormat() {
        return "E | " + super.getStorageFormat() + " | " + this.at;
    }

    /**
     * Returns a String representation of {@code Event} task in display format.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getFormattedDateString() + ")";
    }
}
