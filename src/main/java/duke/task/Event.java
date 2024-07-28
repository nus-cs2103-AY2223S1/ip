package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.DukeCommand;

/**
 * A Task which is an Event.
 */
public class Event extends Task {
    /**
     * Constructor for an Event.
     *
     * @param description The description of the Event.
     * @param time The time of the Event.
     */
    public Event(String description, LocalDateTime time) {
        super(description, time);
    }

    @Override
    public DukeCommand getTaskType() {
        return DukeCommand.EVENT;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy h:mm a");
        return "[E]" + super.toString() + " (at: " + time.format(formatter) + ")";
    }
}
