package main.java;

public class Deadline extends Task {

    /**
     * Deadline of 'Deadline' object
     */
    protected String by;

    public Deadline(String description, String by) {
        super(description);
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
                + this.isDone + "|"
                + this.description + "|"
                + this.by;
    }
}
