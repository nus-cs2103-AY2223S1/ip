package duke.task;

import duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    private final LocalDateTime deadline;

    public Deadlines(String task, String deadline, boolean done) throws DukeException {
        super(task, done);
        this.deadline = ConvertDateTime(deadline);
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "[D]" + super.toString() + String.format(" (by: %s)", deadline.format(format));
    }

    @Override
    public String toSaveString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return "deadline " + super.toSaveString() + " " + deadline.format(format);
    }

    private LocalDateTime ConvertDateTime(String dateTime) throws DukeException {
        return Task.getLocalDateTime(dateTime);
    }
}
