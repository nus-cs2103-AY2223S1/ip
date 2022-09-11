package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * Represents an event task with description and date/time the event occurs at.
 */
public class Event extends Task {

    protected LocalDateTime date;
    private String formattedDate;
    /**
     * Creates a event task object.
     * @param description details of task.
     * @param date date,time of when task occurs.
     */

    public Event(String description, LocalDateTime date) {
        super(description);
        this.date = date;
        this.formattedDate = date.format(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm", Locale.ENGLISH));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formattedDate + ")";
    }

    @Override
    public String toStringForStorage() {

        return "E|" + super.toStringForStorage() + "|" + localDateTimeToString();
    }

    String localDateTimeToString() {
        String[] dateOnly = date.toString().split("T", 2);
        return dateOnly[0] + "@" + date.getHour() + ":" + date.getMinute();
    }
}
