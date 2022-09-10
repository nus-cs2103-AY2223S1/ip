package duke.task;

import java.time.LocalDate;

import duke.DukeException;
import duke.Parser;

/**
 * Event tasks.
 */
public class Event extends Task {
    private static final String STRING_FORMAT_STORAGE = "E | %s | %s";
    private static final String STRING_FORMAT_DISPLAY = "[E]%s (at: %s)";

    private final LocalDate at;

    /**
     * Constructor for Deadline.
     *
     * @param description Description of task.
     * @param isComplete Whether task is complete.
     * @param at Event date.
     */
    Event(String description, boolean isComplete, LocalDate at) {
        super(description, isComplete);
        this.at = at;
    }

    /**
     * Constructor for Deadline.
     *
     * @param description Description of task.
     * @param isComplete Whether task is complete.
     * @param at Event date in String format.
     */
    public Event(String description, boolean isComplete, String at) throws DukeException {
        this(description, isComplete, Parser.parseDate(at));
    }

    /**
     * Constructor for Deadline. {@code isComplete} defaults to {@code false};
     *
     * @param description Description of task.
     * @param at Event date in String format.
     */
    public Event(String description, String at) throws DukeException {
        this(description, Task.IS_INCOMPLETE, Parser.parseDate(at));
    }

    /**
     * Returns deadline of task as a LocalDate object.
     *
     * @return Deadline of task.
     */
    LocalDate getDate() {
        return at;
    }

    /**
     * Returns deadline of task as a formatted String.
     *
     * @return Deadline of task.
     */
    String getFormattedDate() {
        return getDate().format(Task.DATE_FORMAT);
    }

    @Override
    public String toStorageFormat() {
        return String.format(STRING_FORMAT_STORAGE, super.toStorageFormat(), at);
    }

    @Override
    public String toString() {
        return String.format(STRING_FORMAT_DISPLAY, super.toString(), getFormattedDate());
    }
}
