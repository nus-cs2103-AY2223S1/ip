package doke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represent the Deadline task. Extends the Task class.
 */
public class Deadline extends Task{

    private LocalDate by;

    /**
     * a public constructor method for the Deadline class.
     *
     * @param description description for the task
     * @param by deadline date
     */
    public Deadline(String description, String by) {
        super(description);
        LocalDate date = LocalDate.parse(by);
        this.by = date;
    }

    /**
     * Returns a LocalDate object representing the deadline of the task.
     *
     * @return a LocalDate deadline of the task.
     */
    @Override
    public LocalDate getTime() {
        return by;
    }

    /**
     * Returns a String representing the type of deadline task
     *
     * @return String "D"
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return a string representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")) + ")";
    }
}
