package duke.task;

import duke.DukeCommand;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime at;

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
