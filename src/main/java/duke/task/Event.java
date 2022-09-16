package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event which is a Task
 */
public class Event extends Task {
    private LocalDateTime at;

    /**
     * Constructs an Event instance, which inherits Task.
     * @param description
     * @param at
     * @return an event instance
     * @see Task
     */
    public Event(String description, LocalDateTime at) {
        super(description, TaskType.EVENT, at);
        this.at = at;
    }

    @Override
    public String getBy() {
        return this.at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        return String.format("%s%s(at: %s)", "[E]", super.toString(), getBy());
    }
}
