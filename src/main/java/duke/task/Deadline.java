package duke.task;

import java.time.LocalDate;

import duke.DukeException;
import duke.Parser;

/**
 * Tasks with a deadline.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructor for Deadline.
     *
     * @param description Description of task.
     * @param isComplete Whether task is complete.
     * @param by Deadline date.
     */
    Deadline(String description, boolean isComplete, LocalDate by) {
        super(description, isComplete);
        this.by = by;
    }

    /**
     * Constructor for Deadline. {@code isComplete} defaults to {@code false}.
     *
     * @param description Description of task.
     * @param by Deadline date.
     */
    Deadline(String description, LocalDate by) {
        this(description, false, by);
    }

    /**
     * Constructor for Deadline.
     *
     * @param description Description of task.
     * @param isComplete Whether task is complete.
     * @param by Deadline date in String format.
     */
    public Deadline(String description, boolean isComplete, String by) throws DukeException {
        this(description, isComplete, Parser.parseDate(by));
    }

    /**
     * Constructor for Deadline. {@code isComplete} defaults to {@code false}.
     *
     * @param description Description of task.
     * @param by Deadline date in String format.
     */
    public Deadline(String description, String by) throws DukeException {
        this(description, false, Parser.parseDate(by));
    }

    /**
     * Returns deadline of task as a LocalDate object.
     *
     * @return Deadline of task.
     */
    LocalDate getDate() {
        return this.by;
    }

    /**
     * Returns deadline of task as a formatted String.
     *
     * @return Deadline of task.
     */
    String getFormattedDate() {
        return this.getDate().format(DATE_FORMAT);
    }

    @Override
    public String toStorageFormat() {
        return "D | " + super.toStorageFormat() + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getFormattedDate() + ")";
    }
}
