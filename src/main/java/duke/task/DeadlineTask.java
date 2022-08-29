package duke.task;

import duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class representing a DeadlineTask.
 */
public class DeadlineTask extends Task {
    protected LocalDate by;

    /**
     * Class constructor for DeadlineTask.
     * 
     * @param description Command string being used to create DeadlineTask.
     * @throws DateTimeParseException If datetime given cannot be parsed.
     * @throws DukeException If command is invalid.
     */
    public DeadlineTask(String description) throws DateTimeParseException, DukeException {
        super();
        this.commandString = description;
        description = description.substring(9);
        String[] split = description.split(" /by ");
        if (split.length < 2) {
            throw new DukeException("Deadline time (indicated by /by separator) is missing.");
        } else if (split.length > 2) {
            throw new DukeException("Multiple usage of /by separator is not allowed.");
        }
        this.description = split[0];
        this.by = LocalDate.parse(split[1]);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy")) + ")";
    }
}
