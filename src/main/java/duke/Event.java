package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that is labelled as an event with a datetime
 */
public class Event extends Task {

    protected String dateTime;

    /**
     * A constructor for the Event class
     *
     * @param description The description of the event
     * @param dt The datetime of the event
     * @throws DateTimeParseException If <code>dt</code> is not in a recognisable format to parse
     */
    public Event(String description, String dt) throws DateTimeParseException {
        super(description);
        try {
            DateTimeFormatter parserFormats = DateTimeFormatter.ofPattern("[dd/MM/yyyy HHmm][dd MMMM yyyy HH:mm]");
            LocalDateTime dtFormatted = LocalDateTime.parse(dt, parserFormats);
            this.dateTime = dtFormatted.format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm"));
        } catch (DateTimeParseException err) {
            System.out.println("I don't recognise this time format."
                    + "\nTry using this format next time: dd/MM/yyyy HHmm");
            this.dateTime = dt;
        }
    }

    /**
     * Obtains the datetime of the event
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
        try {
            DateTimeFormatter parserFormats = DateTimeFormatter.ofPattern("[dd/MM/yyyy HHmm][dd MMMM yyyy HH:mm]");
            LocalDateTime dtFormatted = LocalDateTime.parse(dt, parserFormats);
            this.dateTime = dtFormatted.format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm"));
            response = "Successfully rescheduled the task's datetime from "
                    + previousDT + " to " + this.dateTime;
        } catch (DateTimeParseException err) {
            response = "I don't recognise this time format."
                    + "\nTry using this format next time: dd/MM/yyyy HHmm"
                    + "Successfully rescheduledthe task's datetime from "
                    + previousDT + " to " + this.dateTime;
            this.dateTime = dt;
        }
        return response;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTime + ")";
    }
}
