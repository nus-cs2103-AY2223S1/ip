package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * A Deadline object is a Task object that has a deadline by which the task
 * should be completed.
 *
 */
public class Deadline extends Task {

    protected LocalDate by;
    /**
     * Creates a new Deadline object with a given description and its deadline.
     *
     * @param description the description of the task
     * @param by the date by which the task should be completed
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * The task type for a Deadline object is "D".
     *
     * @return "D"
     */
    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * Returns the String representation of the Deadline object
     *
     * @return String representation of the Deadline object
     */
    @Override
    public String toString() {
        String formattedDate = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }

    /**
     * Formats the details of the Deadline object such that the information can be saved and loaded
     * from files.
     *
     * @return The String representation of the Deadline object in a format that can be
     * saved to files.
     */
    @Override
    String saveStringToFile() {
        return String.format("%s%s\n", super.saveStringToFile(), by);
    }
}