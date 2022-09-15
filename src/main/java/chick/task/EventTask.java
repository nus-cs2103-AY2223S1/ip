package chick.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import chick.ChickException;

/**
 * Class representing an EventTask.
 */
public class EventTask extends Task {
    protected LocalDate at;

    /**
     * Class constructor for EventTask.
     *
     * @param description Command string being used to create EventTask.
     * @throws DateTimeParseException If datetime given cannot be parsed.
     * @throws ChickException If command is invalid.
     */
    public EventTask(String description) throws DateTimeParseException, ChickException {
        super();
        this.commandString = description;
        int descriptionStartIndex = "event ".length();
        description = description.substring(descriptionStartIndex);
        String[] split = description.split(" /at ");
        int correctSplitCount = 2;
        if (split.length < correctSplitCount) {
            throw new ChickException("Deadline time (indicated by /by separator) is missing.");
        } else if (split.length > correctSplitCount) {
            throw new ChickException("Multiple usage of /by separator is not allowed.");
        }
        this.description = split[0];
        this.at = LocalDate.parse(split[1]);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy")) + ")";
    }

}
