package duke.task;

import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * This class inherits from the abstract Task class
 * and encapsulates the logic of a Deadline task.
 */
public class Deadline extends Task {
    /* Due Date field */
    private LocalDateTime dueDate;

    /**
     * Constructor for the Deadline Task.
     *
     * @param description description of the task.
     */
    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Factory Method for the construction of a Deadline for user input.
     *
     * @param in user's input.
     * @return Deadline with the relevant input fields.
     * @throws DukeException if no task or incorrect formatting is given.
     */
    public static Deadline createDeadline(String in) throws DukeException {
        String[] temp = in.split(" */by* ");
        if (temp.length != 2) {
            throw new DukeException("-Deadline- Please follow the format of ~description~ /by dd-MM-yyyy HHmm!\n");
        }
        String description = temp[0];
        String dueDate = temp[1];
        LocalDateTime deadline;
        try {
            deadline = LocalDateTime.parse(dueDate, INPUT_DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new DukeException("-Deadline- Your date needs to be in dd-MM-yyyy HHmm format!\n");
        }

        return new Deadline(description, deadline);
    }

    /**
     * Overriden toString method for the Deadline Task.
     *
     * @return String representation of the Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate.format(OUTPUT_DATE_FORMAT) + ")";
    }

    /**
     * Override save format method from Task class.
     *
     * @return formatted String for the Event task.
     */
    @Override
    public String saveFormat() {
        return String.format("D | %s | %s", super.saveFormat(), this.dueDate.format(OUTPUT_DATE_FORMAT));
    }
}
