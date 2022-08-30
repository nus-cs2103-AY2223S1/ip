package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exceptions.UnrecognisedDateTimeException;

/**
 * Manager class for event tasks. Handles the
 * parsing and formatting of the task itself.
 */
public class Event extends Task {
    private LocalDateTime timing;

    /**
     * Constructor for the Event class.
     * @param taskDescription Description of the task.
     * @param timing Timing of the event to be parsed and read.
     * @throws UnrecognisedDateTimeException When the timing given by user does not follow a certain
     *     format.
     */
    public Event(String taskDescription, String timing) throws UnrecognisedDateTimeException {
        super(taskDescription);
        try {
            //Alter the timing so that it can be properly added in.
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddHHmm");
            this.timing = LocalDateTime.parse(timing, formatter);
        } catch (DateTimeParseException e) {
            throw new UnrecognisedDateTimeException();
        }
    }

    /**
     * Format of parsing:
     * Type of task # status of task # description # timing
     * @return String that is in the parsing format.
     */
    @Override
    public String parseToFile() {
        return String.format("E # %s # %s # %s", super.getStatusIcon(), super.getTaskDescription(), timing);
    }

    /**
     * Returns a formatted event task to the user.
     * @return Formatted event task to the user.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                timing.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
    }
}
