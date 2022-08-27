package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import duke.dukeExceptions.DukeException;

/**
 * Represents a Event in Duke.
 * 
 * @author Ramanathan Kumarappan
 */
public class Event extends Task {
    private LocalDate d;
    private LocalTime t;
    private boolean hasTime;

    /**
     * Constructor for Event.
     * 
     * @param description - The description for the Event
     * @param at - The date and time for the Event (in YYYY-MM-DD HHMM format).
     * @throws DukeException - When the description or at date is invalid.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        String[] dateAndTime = at.split(" ");
        try {
            this.d = LocalDate.parse(dateAndTime[0]);
            if (dateAndTime.length == 2) {
                this.t = LocalTime.parse(dateAndTime[1], DateTimeFormatter.ofPattern("HHmm"));
                this.hasTime = true;
            } else {
                this.hasTime = false;
            }
        } catch (java.time.format.DateTimeParseException e) {
            throw new DukeException("Please enter a valid date and time YYYY-MM-DD HHMM");
        }
    }

    /**
     * Rebuilds the Event from an event save string.
     *
     * @param saveString - The save string containing Event information.
     * @return A Event Task.
     * @throws DukeException - When Event cannot be reconstructed from the given string.
     */
    public static Event taskFromSave(String saveString) throws DukeException {
        String[] tokens = saveString.split(" \\| ");
        String time = tokens[3].equals("true") ? tokens[4] + " " + tokens[5] : tokens[4];
        Event event = new Event(tokens[2], time);
        if (tokens[1].equals("1")) {
            event.markDone();
        }
        return event;
    }

    /**
     * Returns the Event in a save string format.
     *
     * @return The event in a save string format.
     */
    @Override
    public String saveString() {
        return "E | " + super.saveString() +  " | " + this.hasTime + " | " + this.d + " | " 
                + (this.hasTime ? this.t.format(DateTimeFormatter.ofPattern("HHmm")) : "");
    }

    /**
     * Returns a string representation of the Event.
     *
     * @return The string representation of the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " 
                + this.d.format(DateTimeFormatter.ofPattern(("MMM d yyyy")))
                + (this.hasTime ? " " + this.t : "")  
                + ")";
    }
}
