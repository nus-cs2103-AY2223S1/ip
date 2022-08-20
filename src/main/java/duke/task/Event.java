package duke.task;

import duke.common.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate localDate;

    public Event(String description, boolean isDone, LocalDate localDate) throws DukeException {
        super("event", description, isDone);
        this.localDate = localDate;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + this.localDate.format(DateTimeFormatter.ofPattern("MMM d, yyyy")) + ")";
    }

    @Override
    public String encode() {
        return super.encode() + Task.ENCODING_SEPARATOR + this.localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
