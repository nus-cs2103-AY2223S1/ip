package duke.task;

/**
 * Deadline Task.
 * Deadline date is marked through the user input with the "/by" keyword.
 */
public class Deadline extends TimedTask {


    /**
     * @param description description of the deadline.
     * @param by YYYY-MM-DD format of the deadline.
     */
    public Deadline(String description, String by) {
        super(description, by);
    }

    /**
     * @return toString.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + super.by + ")";
    }

    /**
     * @return details of Task to be stored in file.
     */
    @Override
    public String toFileString() {
        return String.format("D | %s | %s | %s", getStatusIcon(), super.description, super.by);
    }

}
