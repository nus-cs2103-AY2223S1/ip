package bob;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents Event object, a task with a specific occurrence date
 */
public class Event extends Task {

    protected LocalDate at;

    /**
     * Constructor for Event object with description and occurrence date
     *
     * @param description name or details of event
     * @param at date of event
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * updates either name or date of Event Task with updateInfo
     *
     * @param updateInfo info to update for Event Task
     * @throws DateTimeParseException if date format is incorrect
     */
    @Override
    public void updateTask(String updateInfo) throws DateTimeParseException {
        if (updateInfo.startsWith("/")) {
            LocalDate updateDate = LocalDate.parse(updateInfo.substring(1));
            this.at = updateDate;
        } else {
            this.description = updateInfo;
        }
    }


    /**
     * Returns the save format of the Event object
     *
     * @return String representing how Event object is saved
     */
    @Override
    public String toSaveFormat() {
        return "E | " + super.toSaveFormat() + " | " + at;
    }

    /**
     * Returns the string representation of the Event object
     *
     * @return String representation of Event object
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
