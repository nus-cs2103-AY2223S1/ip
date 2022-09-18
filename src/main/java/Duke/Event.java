package Duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * This is the event class that is being called
 * by users
 */
public class Event extends Task{
    protected String at;
    protected LocalDate dateTime;

    /**
     * Constructor for the class Event
     * @param description   Description of the event
     * @param at            Time of the event
     */
    public Event(String description, String at) {
        super(description);
        assert description != "": "Description should not be empty";
        this.at = at;
        dateTime = LocalDate.parse(at, DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
    }
    @Override
    public String toString() {

        return "[E]" + super.toString() + "(at: " + at + ")";
    }

    @Override
    public String dTString() {
        return "[E]" + super.dTString() + " (by: " + this.dateTime.format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.FULL)) + ")";
    }
}