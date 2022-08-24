package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.DukeCommand;

/**
 * A Task which is an Event.
 */
public class Event extends Task {
    protected LocalDateTime at;

    /**
     * Constructor for an Event.
     * @param description The description of the Event.
     * @param at The time of the Event.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public DukeCommand getTaskType() {
        return DukeCommand.EVENT;
    }

    @Override
    public String getOtherData() {
        return this.at.toString();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy h:mm a");
        return "[E]" + super.toString() + " (at: " + at.format(formatter) + ")";
    }
}
