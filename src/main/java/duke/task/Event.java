package duke.task;

import duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private final LocalDateTime date;
    private final String at;

    public Event(String description, String at) throws DukeException {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy HH:mm");
        this.at = at;
        try {
            this.date = LocalDateTime.parse(at, formatter);
        } catch (Exception e) {
            throw new DukeException("Please enter date and time in the format: dd/M/yyyy HH:mm");
        }
    }

    public String getAt() { return at; }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mma")) + ")";
    }
}
