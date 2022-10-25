package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event, which is a specific type of task.
 *
 * @author Liu Han
 */
public class Event extends Task {
    protected LocalDate at;
    private static String printFormat = "MMM d yyyy";
    private static String saveFormat = "yyyy-MM-dd";

    /**
     * Event Constructor
     * @param description Description of the event.
     * @param at Date of the event happening.
     * @throws DukeException If date is in an invalid format.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException err) {
            throw new DukeException("The date format should be: yyyy-MM-dd");
        }

        // this.at = LocalDate.parse(at, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Prints the event when list is called by the user
     * @return String in the format <b>[E][isDone] description (at: MMM d yyyy)</b>.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.at.format(DateTimeFormatter.ofPattern(printFormat)) + ")";
    }

    /**
     * Saves the event into a string and writes into a .txt file.
     * @return String in the format <b>E | isDone | description | yyyy-MM-dd</b>.
     */
    @Override
    public String toSave() {
        return "E | " + (this.isDone ? "1 | " : "0 | ") + this.getDescription() +
                " | " + this.at.format(DateTimeFormatter.ofPattern(saveFormat)) + "\n";
    }
}