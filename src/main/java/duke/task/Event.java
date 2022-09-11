package duke.task;

import java.time.LocalDate;

import duke.DukeException;
import duke.util.Parser;

/**
 * Event task.
 */
public class Event extends Task {
    private LocalDate startTime;

    /**
     * Constructor for {@code Event}. With event time {@code LocalDate}.
     *
     * @param description Description of the {@code Event} task.
     * @param isDone Completion status of the {@code Event} task.
     * @param startTime {@code LocalDate} of event.
     */
    public Event(String description, boolean isDone, LocalDate startTime) {
        super(description, isDone);
        this.startTime = startTime;
    }

    /**
     * Constructor for {@code Event}. With event date string.
     *
     * @param description Description of the {@code Event} task.
     * @param isDone Completion status of the {@code Event} task.
     * @param startTime Event date string.
     * @throws DukeException Checked exceptions that may occur when parsing date string.
     */
    public Event(String description, boolean isDone, String startTime) throws DukeException {
        this(description, isDone, Parser.parseDate(startTime));
    }

    /**
     * Constructor for {@code Event}. With event date string.
     * And assumes completion status is {@code false}.
     *
     * @param description Description of the {@code Event} task.
     * @param startTime Event date string.
     * @throws DukeException Checked exceptions that may occur when parsing date string.
     */
    public Event(String description, String startTime) throws DukeException {
        this(description, false, Parser.parseDate(startTime));
    }

    String getFormattedDateString() {
        return this.startTime.format(DATE_FORMATTER);
    }

    /**
     * Returns a String representation of the {@code Event} task in storage format.
     *
     * @return String representation of the {@code Event} task in storage format.
     */
    @Override
    public String getStorageFormat() {
        return String.format("E | %s | %s", super.getStorageFormat(), this.startTime);
    }

    /**
     * Returns a String representation of {@code Event} task in display format.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.getFormattedDateString());
    }
}
