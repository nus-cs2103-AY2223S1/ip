package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.DukeCommand;

/**
 * A Task which is a Deadline.
 */
public class Deadline extends Task {
    /**
     * Constructor for a Deadline.
     *
     * @param description The description of the Deadline.
     * @param time The deadline.
     */
    public Deadline(String description, LocalDateTime time) {
        super(description, time);
    }

    @Override
    public DukeCommand getTaskType() {
        return DukeCommand.DEADLINE;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy h:mm a");
        return "[D]" + super.toString() + " (by: " + time.format(formatter) + ")";
    }
}
