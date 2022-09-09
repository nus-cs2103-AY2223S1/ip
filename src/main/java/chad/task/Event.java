package chad.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * chad.task.Event task that is a child of task
 */
public class Event extends Task {
    protected LocalDateTime dateTime;

    /**
     * Constructor for event
     * Example: event party /at 2/12/2020 1800
     * @param description description of event
     * @param dateTime date and time of event
     */
    public Event(String description, LocalDateTime dateTime) {
        super(description);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
//        this.dateTime = LocalDateTime.parse(dateTime, formatter);
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.dateTime.format(OUTPUT_DATEFORMAT) + ")";
    }

}
