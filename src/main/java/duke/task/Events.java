package duke.task;

import duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    private final LocalDateTime timing;

    public Events (String task, String timing, boolean done) throws DukeException {
        super(task, done);
        this.timing = ConvertDateTime(timing);
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "[E]" + super.toString() + String.format(" (at: %s)", timing.format(format));
    }

    @Override
    public String toSaveString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return "event " + super.toSaveString() + " " + timing.format(format);
    }

    public LocalDateTime ConvertDateTime(String dateTime) throws DukeException {
        return Task.getLocalDateTime(dateTime);
    }
}
