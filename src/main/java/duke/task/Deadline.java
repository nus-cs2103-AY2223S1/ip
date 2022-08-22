package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Defines <Code>Deadline</Code> class
 */
public class Deadline extends Task {

    /** Deadline of <Code>Deadline</Code> object. */
    protected LocalDate by;

    /**
     * Constructor for <Code>Deadline</Code>.
     * @param description Description of task.
     * @param by          Deadline of task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor for <Code>Deadline</Code> with <Code>isDone</Code> given.
     * @param description Description of task.
     * @param by          Deadline of task.
     * @param isDone      Whether task is done.
     */
    public Deadline(String description, LocalDate by, Boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Overrides <Code>toString</Code> to return status and description of
     * <Code>Deadline</Code>.
     * @return [D][COMPLETION STATUS][TASK DESCRIPTION]
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String date = by.format(formatter);
        return "[D]" + super.toString() + " (by: " + date + ")";
    }

    /**
     * To produce a <Code>String</Code> with "|" delimiters for storing the
     * <Code>Deadline</Code>'s data in a text file.
     * @return "deadline|[COMPLETION STATUS]|[TASK DESCRIPTION]|[TASK DEADLINE]
     */
    public String toFileFormat() {
        return "deadline" + "|" + super.toFileFormat() + "|" + this.by;
    }
}
