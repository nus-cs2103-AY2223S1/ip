package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class that encapsulates the Event task.
 */
public class Event extends Task {

    /** The date of the event */
    private final LocalDate date;

    /**
     * The class constructor.
     *
     * @param description The description of the event.
     * @param date The date of the event.
     */
    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Returns the string to be saved in the text file to be
     * loaded in later.
     *
     * @return The string identifying the details of the task.
     */
    @Override
    public String stringToSave() {
        return "E|" + ("X".equals(super.getStatusIcon()) ? "1|" : "0|") + super.description + "|" + this.date;
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return The string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.description + " (at: " +
                this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
