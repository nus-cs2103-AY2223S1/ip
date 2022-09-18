package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task that also has a deadline to it. Each <code>Deadline</code> object contains a
 * <code>LocalDate</code> time
 */
public class Deadline extends Task {
    protected LocalDate time;

    /**
     * Constructor Method of the Deadline class
     * @param description
     * @param time
     */
    public Deadline(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    /**
     * Constructor Method of the Deadline class
     * @param isDone
     * @param description
     * @param time
     */
    public Deadline(boolean isDone, String description, LocalDate time) {
        super(isDone, description);
        this.time = time;
    }

    /**
     * Converts the Deadline object to a string
     * @return string representation of Deadline
     */
    @Override
    public String toString() {
        String s = super.toString();
        return "[D]" + s + " \n   (by: " + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public String toShortString() {
        String s = super.toString();
        return "[D]" + s;
    }

    /**
     * Converts Deadline Object to a string that will be stored in a file
     * @return  condensed String represenation of Deadline Object
     */
    @Override
    public String toFile() {
        String s = super.toFile();
        return "D," + s + "," + time;
    }

    public LocalDate getDeadline() {
        return this.time;
    }
}
