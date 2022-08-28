package Duke.Task;

import Duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTask extends Task {
    protected LocalDate at;

    public EventTask(String description) throws DateTimeParseException, DukeException {
        super();
        this.commandString = description;
        description = description.substring(6);
        String[] split = description.split(" /at ");
        if (split.length < 2) {
            throw new DukeException("Deadline time (indicated by /by separator) is missing.");
        } else if (split.length > 2) {
            throw new DukeException("Multiple usage of /by separator is not allowed.");
        }
        this.description = split[0];
        this.at = LocalDate.parse(split[1]);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy")) + ")";
    }

}
