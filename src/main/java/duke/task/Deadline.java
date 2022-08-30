package duke.task;

import duke.DukeException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline-type of task
 *
 * @author Derrick Khoo
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a deadline-type task.
     *
     * @param description the description of the task
     * @param by          the date of the deadline
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string description of the deadline task for saving the task
     * to the hard disk.
     *
     * @return the string description
     */
    public String toFileDescription() {
        return "D" + " | " + super.toFileDescription() + " | " + this.by;
    }

    /**
     * Returns a deadline-type task from the string description stored in the hard disk.
     *
     * @param input the string description of the deadline task
     * @return the deadline-type task
     * @throws DukeException if there is an error parsing input to a LocalDate
     */
    public static Deadline fromFileDescription(String input) throws DukeException {
        String[] strArray = input.split(" \\| ", 4);
        String description = strArray[2];
        String by = strArray[3];
        try {
            LocalDate dateBy = LocalDate.parse(by);
            Deadline deadline = new Deadline(description, dateBy);
            if (strArray[1].equals("1")) {
                deadline.markDone();
            }
            return deadline;
        } catch (DateTimeException e) {
            throw new DukeException("Enter the date in yyyy-mm-dd please!");
        }
    }

    /**
     * Returns a boolean denoting if the event is happening at the queried date.
     *
     * @param localDate the queried date
     * @return true if and only if the queried date is the same as the deadline-type
     * task's deadline date.
     */
    public boolean isHappeningOnDate(LocalDate localDate) {
        return this.by.equals(localDate);
    }

    /**
     * Returns a string representation of the task.
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by.format(DateTimeFormatter.ofPattern("MMMM d yyyy")) + ")";
    }
}
