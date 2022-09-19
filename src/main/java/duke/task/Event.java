package duke.task;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Represents an event which is a task with date and time.
 */
public class Event extends Task {

    /** Date and time of the event */
    private LocalDateTime dateTime;

    /**
     * Constructs a new event task with given
     * description and date and time of the event.
     *
     * @param description of the event.
     * @param dateAndTime of the event.
     */
    public Event(String description, String dateAndTime) {
        super(description);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.dateTime = LocalDateTime.parse(dateAndTime, formatter);
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (at: "
                + this.dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mma"))
                + ")";
    }

    @Override
    public String toMemoryString() {
        return "E"
                + " | "
                + (this.isDone() ? "1" : "0")
                +  " | "
                + this.getName()
                + " | "
                + this.dateTime.toLocalDate()
                + " "
                + this.dateTime.toLocalTime().format(DateTimeFormatter.ofPattern("HHmm"));
    }


    public LocalDateTime getLocalDateTime() {
        return this.dateTime;
    }
}
