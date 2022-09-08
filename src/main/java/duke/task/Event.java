package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

/**
 * Represents an event-type of task.
 *
 * @author Derrick Khoo
 */
public class Event extends Task {
    public static final int SPLIT_LIMIT_EVENT = 4;
    public static final int INDEX_OF_DESCRIPTION = 2;
    public static final int INDEX_OF_DATE_AT = 3;
    protected LocalDate at;

    /**
     * Constructs an event-type of task.
     *
     * @param description the description of the task
     * @param at the date when the task is happening at
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns a string description of the event task for saving the task
     * to the hard disk.
     *
     * @return the string description
     */
    public String toFileDescription() {
        return "E" + " | " + super.toFileDescription() + " | " + this.at;
    }

    /**
     * Returns an event-type task from the string description stored in the hard disk.
     *
     * @param input the string description of the event task
     * @return the event-type task
     * @throws DukeException if there is an error parsing the input to a LocalDate
     */
    public static Event fromFileDescription(String input) throws DukeException {
        String[] strArray = input.split(" \\| ", SPLIT_LIMIT_EVENT);
        String description = strArray[INDEX_OF_DESCRIPTION];
        String at = strArray[INDEX_OF_DATE_AT];
        try {
            LocalDate dateAt = LocalDate.parse(at);
            Event event = new Event(description, dateAt);
            if (strArray[1].equals("1")) {
                event.markDone();
            }
            return event;
        } catch (DateTimeParseException e) {
            throw new DukeException("Enter the date in yyyy-mm-dd format please!");
        }
    }

    /**
     * Returns a boolean denoting if the event is happening at the queried date.
     *
     * @param localDate the queried date
     * @return true if and only if the queried date is the same as the event-type
     *      task's event date.
     */
    public boolean isHappeningOnDate(LocalDate localDate) {
        return this.at.equals(localDate);
    }

    /**
     * Returns a string representation of this event.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at.format(DateTimeFormatter.ofPattern("MMMM d yyyy")) + ")";
    }
}
