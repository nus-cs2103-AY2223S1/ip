package duke.task;

import duke.exception.DukeException;

/**
 * Represents a task of type Event.
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructs the Event task.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        this.at = at;
        if (description.isBlank()) {
            throw new DukeException("Take me seriouslyy :( What do you want to do?\n");
        }
        if (at.isBlank()) {
            throw new DukeException("Tell me more about the time/place pretty pls.\n");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
