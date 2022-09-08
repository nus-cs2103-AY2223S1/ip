package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
    public Deadline(String description, String dt) {
        super(description);
        try {
            DateTimeFormatter parserFormats = DateTimeFormatter.ofPattern("[dd/MM/yyyy HHmm][dd MMMM yyyy HH:mm]");
            LocalDateTime dtFormatted = LocalDateTime.parse(dt, parserFormats);
            this.dateTime = dtFormatted.format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm"));
        } catch (DateTimeParseException err) {
            System.out.println("I don't recognise this time format.\nTry using this format next time: dd/MM/yyyy HHmm");
            this.dateTime = dt;
        }
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
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTime + ")";
    }
}
