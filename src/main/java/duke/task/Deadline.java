package duke.task;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;

/**
 * Represents a task that has a deadline.
 */
public class Deadline extends Task {

    protected String dateTime;

    /**
     * A constructor for the Deadline class
     *
     * @param description Description of the task
     * @param dt Datetime of the deadline given to the task
     */
    public Deadline(String description, String dt) throws DateTimeParseException {
        super(description);
        DateTimeFormatter parserFormats = DateTimeFormatter.ofPattern("[dd/MM/yyyy HHmm][dd MMMM yyyy HH:mm]");
        LocalDateTime dtFormatted = LocalDateTime.parse(dt, parserFormats);
        this.dateTime = dtFormatted.format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm"));
    }

    /**
     * Obtains the datetime of this deadline task
     *
     * @return String of the datetime
     */
    public String getDatetime() {
        return this.dateTime;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String setDatetime(String dt) throws DateTimeParseException {
        String response = null;
        String previousDT = this.dateTime;
        DateTimeFormatter parserFormats = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dtFormatted = LocalDateTime.parse(dt, parserFormats);
        this.dateTime = dtFormatted.format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm"));
        response = "Successfully rescheduled the task's datetime from " + previousDT + " to " + this.dateTime;

        return response;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTime + ")";
    }
}
