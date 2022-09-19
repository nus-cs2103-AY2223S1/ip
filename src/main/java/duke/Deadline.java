package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline, which is a specific type of task.
 *
 * @author Liu Han
 */
public class Deadline extends Task {
    protected LocalDate by;
    private static String printFormat = "MMM d yyyy";
    private static String saveFormat = "yyyy-MM-dd";

    /**
     * Deadline Constructor
     * @param description Description of the deadline.
     * @param by Date of the deadline due time.
     * @throws DukeException If date is in an invalid format.
     */
    public Deadline(String description, String by) throws DukeException{
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException err) {
            throw new DukeException("The date format should be: yyyy-MM-dd");
        }

        // this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Prints the deadline when list is called by the user
     * @return String in the format <b>[D][isDone] description (by: MMM d yyyy)</b>.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                this.by.format(DateTimeFormatter.ofPattern(printFormat)) + ")";
    }

    /**
     * Saves the deadline into a string and writes into a .txt file.
     * @return String in the format <b>D | 1/0 | description | yyyy-MM-dd</b>.
     */
    @Override
    public String toSave() {
        return "D | " + (this.isDone ? "1 | " : "0 | ") + this.getDescription() +
                " | " + this.by.format(DateTimeFormatter.ofPattern(saveFormat)) + "\n";
    }
}