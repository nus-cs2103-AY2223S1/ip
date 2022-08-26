package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {

    protected LocalDateTime dateTime;
    private String formattedDateTime;
    private String oldDate;

    /**
     * Default constructor for Event.
     * @param description Description of task.
     * @param dateTime Datetime of task.
     */
    public Event(String description, String dateTime) {
        super(description);
        this.oldDate = dateTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        this.dateTime = LocalDateTime.parse(dateTime, formatter);
        formattedDateTime = this.dateTime.format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.FULL)) + " " + this.dateTime.format(DateTimeFormatter
                .ofLocalizedTime(FormatStyle.SHORT));
    }

    public String getDate() {
        return formattedDateTime;
    }

    public String getOldDate() {
        return this.oldDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formattedDateTime + ")";
    }
}
