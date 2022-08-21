package duke.task;

import duke.DukeException;
import duke.Parser;

import java.time.LocalDate;

/**
 * Tasks with a deadline.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructor for Deadline.
     * 
     * @param description description of task.
     * @param isComplete whether task is complete.
     * @param by deadline date.
     */
    Deadline(String description, boolean isComplete, LocalDate by) {
        super(description, isComplete);
        this.by = by;
    }

    /**
     * Constructor for Deadline. {@code isComplete} defaults to {@code false}.
     * 
     * @param description description of task.
     * @param by deadline date.
     */
    Deadline(String description, LocalDate by) {
        this(description, false, by);
    }

    /**
     * Constructor for Deadline.
     * 
     * @param description description of task.
     * @param isComplete whether task is complete.
     * @param by deadline date in String format.
     */
    public Deadline(String description, boolean isComplete, String by) throws DukeException {
        this(description, isComplete, Parser.parseDate(by));
    }

    /**
     * Constructor for Deadline. {@code isComplete} defaults to {@code false}.
     * 
     * @param description description of task.
     * @param by deadline date in String format.
     */
    public Deadline(String description, String by) throws DukeException {
        this(description, false, Parser.parseDate(by));
    }

    /**
     * Returns deadline of task as a LocalDate object.
     * 
     * @return deadline of task.
     */
    LocalDate getDate() {
        return this.by;
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
        return "D | " + super.toStorageFormat() + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getFormattedDate() + ")";
    }
}
