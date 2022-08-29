package chad.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline task that inherits from task
 */
public class Deadline extends Task{
    protected LocalDateTime dateTime;

    public Deadline(String description, String byDate) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        this.dateTime = LocalDateTime.parse(byDate,formatter);
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                this.dateTime.format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm a")) +")";
    }
}
