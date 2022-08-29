package duke.task;

import duke.exception.EmptyDescException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String at;
    protected LocalDate d;

    public Event(String desc, String at) throws EmptyDescException {
        super(desc);
        this.at = at;
        d = LocalDate.parse(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + d.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toSave() {
        return "E | " + (isDone ? "1" : "0") + " |" + desc + " | " + at;
    }
}
