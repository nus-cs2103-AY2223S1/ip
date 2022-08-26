package duke.entities;

import java.time.LocalDateTime;

import duke.exceptions.DukeException;

/**
 * Deadline task that has a description and a deadline
 */
public class Deadline extends Event {
    public Deadline(String desc, LocalDateTime deadline) throws DukeException {
        super(desc, deadline);
    }

    @Override
    public String toString() {
        String marker = isDone() ? "[X] " : "[ ] ";
        return "[D]" + marker + getDescription() + getDeadline();
    }
}
