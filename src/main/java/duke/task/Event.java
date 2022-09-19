package duke.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Represents a task that occurs at a specific time.
 */
public class Event extends DatedTask {

    private LocalDateTime at;

    /**
     * Initialises an Event with its description and date.
     * @param description Description of the event.
     * @param at DateTime that event occurs.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public LocalDateTime getDate() {
        return at;
    }

    @Override
    public int compareTo(DatedTask task) {
        return this.at.isAfter(task.getDate()) ? 1 : -1;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + super.toString() + " (at: " + at.format(formatter) + ")";
    }

}
