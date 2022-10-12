package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event in the task list
 */
public class Event extends Task {
    protected LocalDate time;

    /**
     * Constructor Method of Event class
     * @param description
     * @param time
     */
    public Event(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    /**
     * Constructor Method of Event class
     * @param isDone
     * @param description
     * @param time
     */
    public Event(boolean isDone, String description, LocalDate time) {
        super(isDone, description);
        this.time = time;
    }

    /**
     * Converts Event Object to String
     * @return String representation of Event Object
     */
    @Override
    public String toString() {
        String s = super.toString();
        return "[E]" + s + " \n   (at: " + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Converts Event object to string for storage
     * @return shortend string form of the event object
     */
    public String toShortString() {
        String s = super.toString();
        return "[E]" + s;
    }

    /**
     * Converts Event Object to a String to be stored in a file
     * @return condensed String representation of Event object
     */
    public String toFile() {
        String s = super.toFile();
        return "E," + s + "," + time;
    }
}
