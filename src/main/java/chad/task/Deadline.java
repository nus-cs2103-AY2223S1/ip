package chad.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline task that inherits from task
 */
public class Deadline extends Task {
    protected LocalDateTime dateTime;

    /**
     * Constructor for Deadline
     * Example: deadline return book /by 2/12/2019 1800
     * @param description description of deadline
     * @param dateTime date and time of when deadline is due
     */
    public Deadline(String description, LocalDateTime dateTime) {
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
        return "[D]" + super.toString() + " (by: "
                + this.dateTime.format(OUTPUT_DATEFORMAT) + ")";
    }
}
