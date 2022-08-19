package duke.task;

import duke.DukeException;

import java.time.LocalDate;

public class Event extends Task {
    private LocalDate at;

    Event(String description, boolean isComplete, LocalDate at) {
        super(description, isComplete);
        this.at = at;
    }

    Event(String description, LocalDate at) {
        this(description, false, at);
    }

    public Event(String description, boolean isComplete, String at) throws DukeException {
        this(description, isComplete, Task.parseDate(at));
    }

    public Event(String description, String at) throws DukeException {
        this(description, false, Task.parseDate(at));
    }

    LocalDate getDate() {
        return this.at;
    }

    String getFormattedDate() {
        return this.getDate().format(DATE_FORMAT);
    }

    @Override
    public String toStorageFormat() {
        return "E | " + super.toStorageFormat() + " | " + this.at;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (at: " + this.getFormattedDate() + ")";
    }
}
