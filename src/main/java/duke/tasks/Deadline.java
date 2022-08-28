package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.dukeExceptions.DukeException;

/**
 * Represents a Deadline in Duke.
 * 
 * @author Ramanathan Kumarappan
 */
public class Deadline extends Task {
    private LocalDate date;
    private LocalTime time;
    private boolean hasTime;

    /**
     * Constructor for Deadline.
     * 
     * @param description - The description of the Deadline.
     * @param by - The date and time for the deadline (in YYYY-MM-DD HHMM format).
     * @throws DukeException - When the description or by date is invalid.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        String[] dateAndTime = by.split(" ");
        try {
            this.date = LocalDate.parse(dateAndTime[0]);
            if (dateAndTime.length == 2) {
                this.time = LocalTime.parse(dateAndTime[1], DateTimeFormatter.ofPattern("HHmm"));
                this.hasTime = true;
            } else {
                this.hasTime = false;
            }
        } catch (java.time.format.DateTimeParseException e) {
            throw new DukeException("Please enter a valid date and time (YYYY-MM-DD HHMM) or date only (YYYY-MM-DD)");
        }
    }

    /**
     * Rebuilds the Deadline from a deadline save string.
     * 
     * @param saveString - The save string containing deadline information.
     * @return A deadline Task.
     * @throws DukeException - When Deadline cannot be reconstructed from the given string.
     */
    public static Deadline taskFromSave(String saveString) throws DukeException {
        String[] tokens = saveString.split(" \\| ");
        String time = tokens[3].equals("true") ? tokens[4] + " " + tokens[5] : tokens[4]; 
        Deadline deadline = new Deadline(tokens[2], time);
        if (tokens[1].equals("1")) {
            deadline.markDone();
        }
        return deadline;
    }

    /**
     * Returns the Deadline in a save string format.
     * 
     * @return The deadline in a save string format.
     */
    @Override
    public String saveString() {
        return "D | " + super.saveString() + " | " + this.hasTime + " | " + this.date + " | " 
                + (this.hasTime ? this.time.format(DateTimeFormatter.ofPattern("HHmm")) : "");
    }

    /**
     * Returns a string representation of the Deadline.
     * 
     * @return The string representation of the Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " 
                + this.date.format(DateTimeFormatter.ofPattern(("MMM d yyyy"))) 
                + (this.hasTime ? " " + this.time : "") 
                + ")";
    }
}
