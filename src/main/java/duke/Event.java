package duke;

import java.time.LocalDate;

/**
 * Class used to represent an Event type task that has a start and end datetime.
 */
public class Event extends Task {
    protected LocalDate eventDate;

    public Event(String taskName, boolean isDone, LocalDate eventDate) {
        super(taskName, isDone);
        this.eventDate = eventDate;
    }

    @Override
    public String toSaveFormatString() {
        return String.format("E|%d|%s|%s", isDone ? 1 : 0, taskName, eventDate);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), eventDate);
    }
}
