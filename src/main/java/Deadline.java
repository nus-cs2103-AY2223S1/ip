package main.java;

public class Deadline extends Task {

    /**
     * Deadline of 'Deadline' object
     */
    protected String by;

    /**
     * Constructor method for `Deadline`.
     * @param description Description of task.
     * @param by          Deadline of task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor method for `Deadline`.
     * @param description Description of task.
     * @param by          Deadline of task.
     * @param isDone      Whether task is done.
     */
    public Deadline(String description, String by, Boolean isDone) {
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
        return "[D]" + super.toString() + " (by: " + by + ")";
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
}
