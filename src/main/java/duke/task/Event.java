package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An Event class that encapsulates the information of an Event task.
 */
public class Event extends Task {

    private LocalDateTime at;

    /**
     * Constructs an Event class.
     *
     * @param description the description of the Event task.
     * @param at          the date of the event.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructs an Event class.
     *
     * @param description the description of the Event task.
     * @param at          the date of the event.
     * @param status      to indicate whether the task has been done
     */
    public Event(String description, LocalDateTime at, String status) {
        this(description, at);

        final String DONE = "1";

        if (status.equals(DONE)) {
            super.markAsDone();
        }
    }

    @Override
    public String getTaskType() {
        return "Event";
    }

    @Override
    public LocalDateTime getDate() {
        return at;
    }

    @Override
    public String toString() {
        String dateString = at.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        return "[E]" + super.toString() + " (at: " + dateString + ")";
    }
}
