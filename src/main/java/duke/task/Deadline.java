package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    /**
     * Deadline of 'Deadline' object
     */
    protected LocalDate by;

    /**
     * Constructor method for `Deadline`.
     * @param description Description of task.
     * @param by          Deadline of task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor method for `Deadline`.
     * @param description Description of task.
     * @param by          Deadline of task.
     * @param isDone      Whether task is done.
     */
    public Deadline(String description, LocalDate by, Boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Override 'toString' method to return status and description of
     * 'Deadline' object.
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
     * To produce a String with "|" delimiters for storing the task's data
     * into a text file.
     * @return "deadline|[COMPLETION STATUS]|[TASK DESCRIPTION]|[TASK DEADLINE]
     */
    public String toFileFormat() {
        return "deadline" + "|"
                + super.toFileFormat() + "|"
                + this.by;
    }

    /**
     * Returns whether <Code>Deadline</Code>'s description or deadline contains
     * given word.
     * @param word Word to search for.
     * @return     <Code>Boolean</Code> value of whether
     *             <Code>Deadline</Code>'s description or date contains given
     *             word.
     */
    @Override
    public Boolean hasWord(String word) {
        return super.hasWord(word) || this.by.toString().contains(word);
    }
}
