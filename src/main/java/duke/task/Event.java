package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * An event task
 *
 * @author Pontakorn Prasertsuk
 */
public class Event extends Task {

    public static final String SYMBOL = "E";

    protected final LocalDate date;

    /**
     * Constructs a new Event instance
     *
     * @param title the name of the task
     * @param isDone whether the task is completed or not
     * @param date the date where the event occurs
     */
    public Event(String title, boolean isDone, String date) throws DateTimeParseException {
        super(title, isDone);
        this.date = LocalDate.parse(date);
    }

    /**
     * Encodes the task for saving into the file
     *
     * @return the string to be saved into the file
     */
    @Override
    public String encode() {
        return (SYMBOL + " | " + (this.isDone ? "1" : "0") + " | " + this.title + " | "
                + this.date);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date + ")";
    }
}
