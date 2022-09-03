package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A task that also has a deadline
 *
 * @author Cui Shen Yi
 * @version CS2103T AY22/23 Semester 1
 */
public class Deadline extends Task {
    private LocalDateTime dueDate;

    /**
     * Constructor to create a new duke.Deadline
     *
     * @param task    the task that you want to complete (String)
     * @param dueDate the dueDate for this deadline (String)
     */
    public Deadline(String task, String dueDate) throws DukeException {
        super(task);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            this.dueDate = LocalDateTime.parse(dueDate, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date Format Incorrect: yyyy-MM-dd HH:mm");
        }
    }

    /**
     * To String method that returns the task in string form to the user
     *
     * @return the task in string format
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[D] " + super.toString() + " (by: " + this.dueDate.format(formatter) + ")";
    }
}
