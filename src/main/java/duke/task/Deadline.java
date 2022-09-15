package duke.task;

import java.time.LocalDateTime;

import duke.exception.DukeException;

/**
 * This class inherits from the abstract Task class
 * and encapsulates the logic of a Deadline task.
 */
public class Deadline extends Task {
    private static final String MISFORMAT_DEADLINE =
            "Please follow the format of ~description~ /by dd-MM-yyyy HHmm /p high/medium/low/none!";

    /* Due Date field */
    private LocalDateTime dueDate;

    /**
     * Constructor for the Deadline Task.
     *
     * @param description Description of the task.
     */
    public Deadline(String description, LocalDateTime dueDate, Priority priority) {
        super(description, priority);
        this.dueDate = dueDate;
    }

    /**
     * Factory Method for the construction of a Deadline for user input.
     *
     * @param in User's input.
     * @return Deadline with the relevant input fields.
     * @throws DukeException If no task or incorrect formatting is given.
     */
    public static Deadline createDeadline(String in) throws DukeException {
        String[] inputArr = splitDescAndDateDeadline(in);

        String[] prioritySplit = splitPriority(inputArr[1]);

        String description = inputArr[0];
        String deadlineString = prioritySplit[0];
        String priorityString = prioritySplit[1];

        Priority priority = parsePriority(priorityString);

        LocalDateTime deadline = parseTime(deadlineString);

        return new Deadline(description, deadline, priority);
    }

    /**
     * Splits the description and date for the Deadline user input based on the /by delimiter.
     *
     * @param in User's input.
     * @return String array containing the description in the 1st address and the date + priority in the 2nd.
     * @throws DukeException if input is misformatted.
     */
    private static String[] splitDescAndDateDeadline(String in) throws DukeException {
        String[] inputArr = in.split(" */by* ");
        if (inputArr.length != 2) {
            throw new DukeException(MISFORMAT_DEADLINE);
        }
        return inputArr;
    }

    /**
     * Overrides toString method for the Deadline Task.
     *
     * @return String representation of the Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate.format(OUTPUT_DATE_FORMAT) + ") " + priority;
    }

    /**
     * Overrides save format method from Task class.
     *
     * @return Formatted String for the Event task.
     */
    @Override
    public String saveFormat() {
        return String.format("D | %s | %s", super.saveFormat(), this.dueDate.format(OUTPUT_DATE_FORMAT));
    }
}
