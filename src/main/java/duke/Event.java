package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDate at;

    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter valid date format (yyyy-mm-dd)");
        }
    }

    @Override
    public String saveTaskAsString() {
        String status = this.isDone ? "1" : "0";
        return String.format("E | %s | %s | %s", status, this.description, this.at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
