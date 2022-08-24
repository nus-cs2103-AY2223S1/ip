package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.DukeCommand;

/**
 * A Task which is a Deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor for a Deadline.
     * @param description The description of the Deadline.
     * @param by The deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public DukeCommand getTaskType() {
        return DukeCommand.DEADLINE;
    }

    @Override
    public String getOtherData() {
        return this.by.toString();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy h:mm a");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}
