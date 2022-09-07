package doke;

import java.time.LocalDate;

/**
 * Represents the Deadline task. Extends the Task class.
 *
 * @author Stevan Gerard Gunawan
 */
public class Deadline extends Task {

    private LocalDate by;
    private String dateCache = "";

    /**
     * a public constructor method for the Deadline class.
     *
     * @param description description for the task
     * @param by deadline date
     */
    public Deadline(String description, String by) {
        super(description);
        System.out.println(by);
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
        if (dateCache.isEmpty()) {
            dateCache = by.format(FORMATTER);
        }
        return "[D]" + super.toString() + " (by: " + dateCache + ")";
    }
}
