package duke.task;

import java.time.LocalDate;

import duke.DukeException;
import duke.Parser;

/**
 * Event tasks.
 */
public class Event extends Task {
    private LocalDate at;

    /**
     * Constructor for Deadline.
     *
     * @param description description of task.
     * @param isComplete whether task is complete.
     * @param by event date.
     */
    Event(String description, boolean isComplete, LocalDate at) {
        super(description, isComplete);
        this.at = at;
    }

    /**
     * Constructor for Deadline. {@code isComplete} defaults to {@code false}.
     *
     * @param description description of task.
     * @param by event date.
     */
    Event(String description, LocalDate at) {
        this(description, false, at);
    }

    /**
     * Constructor for Deadline.
     *
     * @param description description of task.
     * @param isComplete whether task is complete.
     * @param by event date in String format.
     */
    public Event(String description, boolean isComplete, String at) throws DukeException {
        this(description, isComplete, Parser.parseDate(at));
    }

    /**
     * Constructor for Deadline. {@code isComplete} defaults to {@code false};
     *
     * @param description description of task.
     * @param by event date in String format.
     */
    public Event(String description, String at) throws DukeException {
        this(description, false, Parser.parseDate(at));
    }

    /**
     * Returns deadline of task as a LocalDate object.
     *
     * @return deadline of task.
     */
    LocalDate getDate() {
        return this.at;
    }

    /**
     * Returns deadline of task as a formatted String.
     *
     * @return deadline of task.
     */
    String getFormattedDate() {
        return this.getDate().format(DATE_FORMAT);
    }

    @Override
    public String toStorageFormat() {
        return "E | " + super.toStorageFormat() + " | " + this.at;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (at: " + this.getFormattedDate() + ")";
    }
}
