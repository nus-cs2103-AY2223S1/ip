package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Deadline class which is a subclass of Task
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor for Deadline class
     * @param description Description of the deadline
     * @param by Date of the deadline in String format
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Creates a String to represent the deadline task during listing
     * @return String to be displayed when list
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Creates a String to be saved in the file
     * @return String to be displayed in the file
     */
    @Override
    public String savedString() {
        return "D | " + (this.isDone ? "1 | " : "0 | ") + this.description + " | " + this.by;
    }
}