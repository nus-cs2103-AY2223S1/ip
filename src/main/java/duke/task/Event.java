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

    private final LocalDate date;

    /**
     * Constructs a new Event instance
     *
     * @param title  the name of the task
     * @param status whether the task is completed or not
     * @param date   the date where the event occurs
     */
    public Event(String title, boolean status, String date) throws DateTimeParseException {
        super(title, status);
        this.date = LocalDate.parse(date);
    }

    /**
     * Encode the task for saving into the file
     *
     * @return the string to be saved into the file
     */
    @Override
    public String encode() {
        return (SYMBOL + " | " + (this.status ?
                "1" :
                "0") + " | " + this.title + " | " + this.date);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date + ")";
    }
}
