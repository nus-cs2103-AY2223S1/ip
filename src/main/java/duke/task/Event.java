package duke.task;

import java.time.LocalDate;

import duke.DukeException;
import duke.util.Parser;

public class Event extends Task {
    private LocalDate at;

    public Event(String description, boolean isDone, LocalDate at) {
        super(description, isDone);
        this.at = at;
    }

    public Event(String description, boolean isDone, String at) throws DukeException {
        this(description, isDone, Parser.parseDate(at));
    }

    public Event(String description, String at) throws DukeException {
        this(description, false, Parser.parseDate(at));
    }

    String getFormattedDateString() {
        return this.at.format(DATE_FORMATTER);
    }

    @Override
    public String getStorageFormat() {
        return "E | " + super.getStorageFormat() + " | " + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getFormattedDateString() + ")";
    }
}
