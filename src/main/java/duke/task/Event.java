package duke.task;

import java.time.LocalDateTime;

import duke.exception.DukeException;

/**
 * This class inherits from the abstract Task class
 * and encapsulates the logic of an Event task.
 */
public class Event extends Task {
    private static final String MISFORMAT_EVENT =
            "Please follow the format of ~description~ /at dd-MM-yyyy HHmm /p high/medium/low/none!";

    /* Duration field */
    private LocalDateTime at;

    /**
     * Constructor for the Event Task.
     *
     * @param description Description of the task.
     */
    public Event(String description, LocalDateTime duration, Priority priority) {
        super(description, priority);
        this.at = duration;
    }

    /**
     * Factory Method for the construction of an Event for user input.
     *
     * @param in User's input.
     * @return Event with the relevant input fields.
     * @throws DukeException if no task or incorrect formatting is given.
     */
    public static Event createEvent(String in) throws DukeException {
        String[] inputArr = splitDescAndDateEvent(in);

        String[] prioritySplit = splitPriority(inputArr[1]);

        String description = inputArr[0];
        String duration = prioritySplit[0];
        String priorityString = prioritySplit[1];

        Priority priority = parsePriority(priorityString);

        LocalDateTime event = parseTime(duration);

        return new Event(description, event, priority);
    }

    /**
     * Splits the description and date for the Event user input based on the /at delimiter.
     *
     * @param in User's input.
     * @return String array containing the description in the 1st address and the date + priority in the 2nd.
     * @throws DukeException if input is misformatted.
     */
    private static String[] splitDescAndDateEvent(String in) throws DukeException {
        String[] inputArr = in.split(" */at* ");
        if (inputArr.length != 2) {
            throw new DukeException(MISFORMAT_EVENT);
        }
        return inputArr;
    }

    /**
     * Override toString method for the Event Task.
     *
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(OUTPUT_DATE_FORMAT) + ") " + priority;
    }

    /**
     * Override save format method from Task class.
     *
     * @return formatted String for the Event task.
     */
    @Override
    public String saveFormat() {
        return String.format("E | %s | %s", super.saveFormat(), this.at.format(OUTPUT_DATE_FORMAT));
    }
}
