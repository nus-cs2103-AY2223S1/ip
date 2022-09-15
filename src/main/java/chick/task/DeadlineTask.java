package chick.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import chick.ChickException;

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
     * @throws ChickException If command is invalid.
     */
    public DeadlineTask(String description) throws DateTimeParseException, ChickException {
        super();
        this.commandString = description;
        int descriptionStartIndex = "deadline ".length();
        description = description.substring(descriptionStartIndex);
        String[] split = description.split(" /by ");
        int correctSplitCount = 2;
        if (split.length < correctSplitCount) {
            throw new ChickException("Deadline description or deadline time is missing.");
        } else if (split.length > correctSplitCount) {
            throw new ChickException("Multiple usage of /by separator is not allowed.");
        }
        this.description = split[0];
        this.by = LocalDate.parse(split[1]);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy")) + ")";
    }
}
