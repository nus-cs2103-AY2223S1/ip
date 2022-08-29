package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

/**
 * The class represents an Event which is a Task.
 *
 * @author Bryan Ng Zi Hao
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Constructor for Event.
     *
     * @param description The description of the event.
     * @param at The date which the event occurs.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Reads the input file and returns a event task based on
     * the data stored in the file.
     *
     * @param data The extracted data from the file.
     * @return A new event that describes the task.
     * @throws DukeException If date format is invalid.
     */
    public static Event parseFile(String data) throws DukeException {
        String[] details = data.split(" \\| ");
        try {
            Event event = new Event(details[2], LocalDate.parse(details[3]));
            if (details[1].equals("1")) {
                event.markAsDone();
            }
            return event;
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format (yyyy-mm-dd)");
        }
    }

    /**
     * Changes the format of the string description such that
     * it fits with the file's format.
     *
     * @return A new string that fits the file's format.
     */
    @Override
    public String toDataFormat() {
        String completed = "";
        if (this.isDone) {
            completed = "1";
        } else {
            completed = "0";
        }
        return "E | " + completed + " | " + this.getDescription() + " | " + this.at;
    }

    /**
     * Checks if the task is due on the date provided.
     *
     * @param date The date the task is due on.
     * @return a boolean value, true if the task is due on said date.
     */
    @Override
    public boolean isOn(LocalDate date) {
        return this.at.equals(date);
    }

    /**
     * Override the toString() method to display the task.
     *
     * @return A String representing the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.at.format(DateTimeFormatter.ofPattern("E, d MMM yyyy")) + ")";
    }
}
