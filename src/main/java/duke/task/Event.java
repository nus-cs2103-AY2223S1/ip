package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

public class Event extends Task {
    private LocalDateTime at;

    public Event(String title, LocalDateTime at) throws DukeException {
        super(title);
        this.at = at;
    }

    public Event(String title, LocalDateTime at, Boolean isDone) {
        super(title, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_OUTPUT_FORMAT);
        return "[E] " + super.toString() + " (at: " + at.format(formatter) + ")";
    }

    public String toSaveString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_INPUT_FORMAT);
        return "E|" + super.toSaveString() + "|" + at.format(formatter);
    }
}
