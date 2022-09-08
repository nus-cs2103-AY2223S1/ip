package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

/**
 * This class inherits from the abstract Task class
 * and encapsulates the logic of a Deadline task.
 */
public class Deadline extends Task {
    private static final String MISFORMAT_DEADLINE = "Please follow the format of ~description~ /by dd-MM-yyyy HHmm!";

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
        String[] inputArr = in.split(" */by* ");
        if (inputArr.length != 2) {
            throw new DukeException(MISFORMAT_DEADLINE);
        }
        String description = inputArr[0];
        String dateString = inputArr[1];
        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(dateString, INPUT_DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new DukeException(MISFORMAT_DATE);
        }

        return new Deadline(description, dateTime);
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
