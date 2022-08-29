package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

/**
 * The class represents a Deadline which is a Task.
 *
 * @author Bryan Ng Zi Hao
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor for Deadline.
     *
     * @param description The description of the event.
     * @param by The date which the task is due.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Reads the input file and returns a deadline task based on
     * the data stored in the file.
     *
     * @param data The extracted data from the file.
     * @return A new deadline that describes the task.
     * @throws DukeException If date format is invalid.
     */
    public static Deadline parseFile(String data) throws DukeException {
        try {
            String[] details = data.split(" \\| ");
            Deadline deadline = new Deadline(details[2], LocalDate.parse(details[3]));
            if (details[1].equals("1")) {
                deadline.markAsDone();
            }
            return deadline;
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
        return "D | " + completed + " | " + this.getDescription() + " | " + this.by;
    }

    /**
     * Checks if the task is due on the date provided.
     *
     * @param date The date the task is due on.
     * @return a boolean value, true if the task is due on said date.
     */
    @Override
    public boolean isOn(LocalDate date) {
        return this.by.equals(date);
    }

    /**
     * Override the toString() method to display the task.
     *
     * @return A String representing the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("E, d MMM yyyy")) + ")";
    }
}
