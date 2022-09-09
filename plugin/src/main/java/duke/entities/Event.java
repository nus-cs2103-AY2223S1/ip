package duke.entities;

import java.time.LocalDateTime;

import duke.exceptions.DukeException;

/**
 * Event with a description and a deadline
 */
public class Event extends Todo implements Comparable {

    /**
     * Initialises the event with desc and deadline
     * @param desc describes the event
     * @param deadline when the event is supposed to be completed
     * @throws DukeException when there is an error
     */
    public Event(String desc, LocalDateTime deadline) throws DukeException {
        super(desc);
        super.setDeadline(deadline);
    }

    @Override
    public String toString() {
        String marker = isDone() ? "[X] " : "[ ] ";
        return "[E]" + marker + getDescription() + getDeadline();
    }
}
