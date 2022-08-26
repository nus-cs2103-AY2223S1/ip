package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import duke.DukeException;

public class Event extends Task {

    protected LocalDate at;

    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong date/time format. Please use this format: yyyy-mm-dd");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formattedDate() + ")";
    }

    private String formattedDate() {
        return this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

}
