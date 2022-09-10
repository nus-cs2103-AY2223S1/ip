package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Defines {@code Deadline} class.
 */
public class Deadline extends Task {

    /** Deadline of {@code Deadline} object. */
    protected LocalDate by;

    /**
     * Constructor for {@code Deadline}.
     * @param description Description of task.
     * @param by          Deadline of task.
     * @param priority    Priority level of task.
     */
    public Deadline(String description, LocalDate by, Level priority) {
        super(description, priority);
        this.by = by;
    }

    /**
     * Constructor for {@code Deadline} with {@code isDone} given.
     * @param description Description of task.
     * @param by          Deadline of task.
     * @param isDone      Whether task is done.
     * @param priority    Priority level of task.
     */
    public Deadline(String description, LocalDate by, Boolean isDone,
                    Level priority) {
        super(description, isDone, priority);
        this.by = by;
    }

    /**
     * Overrides {@code toString} to return status and description of
     * {@code Deadline}.
     * @return [D][[COMPLETION STATUS]][[PRIORITY]] [TASK DESCRIPTION]
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("MMM dd yyyy");
        String date = by.format(formatter);
        return "[D]" + super.toString() + " (by: " + date + ")";
    }

    /**
     * Produces a {@code String} with "|" delimiters for storing the
     * {@code Deadline}'s data in a text file.
     * @return "deadline|[COMPLETION STATUS]|[TASK DESCRIPTION]
     *                  |[PRIORITY][TASK DEADLINE]"
     */
    public String toFileFormat() {
        return "deadline" + "|" + super.toFileFormat() + "|" + this.by;
    }

    /**
     * Returns whether {@code Deadline}'s description or deadline contains
     * given word.
     * @param word Word to search for.
     * @return     {@code Boolean} value of whether
     *             {@code Deadline}'s description or date contains given
     *             word.
     */
    @Override
    public Boolean hasWord(String word) {
        return super.hasWord(word) || this.by.toString().contains(word);
    }
}
