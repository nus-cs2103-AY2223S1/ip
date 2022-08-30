package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exceptions.UnrecognisedDateTimeException;

/**
 * Manager class for deadline tasks. Handles the
 * parsing and formatting of the task itself.
 */
public class Deadline extends Task {
    private LocalDateTime timing;

    /**
     * Constructor for the Deadline class.
     * @param taskDescription Description of the task.
     * @param timing Timing of the deadline to be parsed and read.
     * @throws UnrecognisedDateTimeException When the timing given by user does not follow a certain
     *     format.
     */
    public Deadline(String taskDescription, String timing) throws UnrecognisedDateTimeException {
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
        return String.format("D # %s # %s # %s", super.getStatusIcon(), super.getTaskDescription(), timing);
    }

    /**
     * Returns a formatted deadline task to the user.
     * @return Formatted deadline task to the user.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                timing.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
    }
}
