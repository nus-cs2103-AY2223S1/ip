package chad.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * chad.task.Event task that is a child of task
 */
public class Event extends Task {
    protected LocalDateTime dateTime;

    public Event(String description, String dateTime) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        this.dateTime = LocalDateTime.parse(dateTime, formatter);
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                this.dateTime.format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm a")) +")";
    }

}
