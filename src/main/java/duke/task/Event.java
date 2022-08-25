package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An Event class that encapsulates the information of an Event task.
 */
public class Event extends Task {

    private final LocalDate at;

    /**
     * Constructs an Event class.
     *
     * @param description the description of the Event task.
     * @param at          the date of the event.
     */
    public Event(String description, LocalDate at) {
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
    public Event(String description, LocalDate at, String status) {
        this(description, at);
        if (status.equals("1")) {
            super.markAsDone();
        }
    }

    @Override
    public String getTaskType() {
        return "Event";
    }

    @Override
    public LocalDate getDate() {
        return at;
    }

    @Override
    public String toString() {
        String dateString = at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + " (at: " + dateString + ")";
    }
}
