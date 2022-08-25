package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    public static final String EVENT_REP = "E";
    protected LocalDate time;

    public Event(String content, String time) throws DukeException {
        super(content);
        try {
            this.time = LocalDate.parse(time);
        } catch (DateTimeParseException e) {
            throw new DukeException("You need to input in yyyy-mm-dd format!");
        }
    }

    @Override
    public String toString() {
        return "[" + EVENT_REP + "]" + super.toString() + " (at: " + this.time.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public String toStorage() {
        return EVENT_REP + super.toStorage() + Task.SEPARATOR + this.time;
    }
}
