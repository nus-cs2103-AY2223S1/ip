package duke.task;

import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime at;

    /**
     * An event class which is a task with a date.
     *
     * @param description Description of the task
     * @param at Time of due date
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public LocalDateTime getDateTime() {
        return this.at;
    }

    /**
     * Formats a string to be written in the file.
     *
     * @return String to be written
     */
    @Override
    public String getWriteString() {
        return String.format("E | %s | %s", super.getWriteString(), this.at);
    }

    @Override
    public String toString() {
        return String.format("[E] %s (at: %s)", super.toString(), this.at);
    }
}