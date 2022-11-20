package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * An Event Task
 *
 * @author Nephelite
 * @version 0.1
 */
public class Event extends Task {
    private final LocalDate date;

    /**
     * Constructor for an Event task
     *
     * @param task the task
     * @param date when the event is taking place
     * @since 0.1
     */
    public Event(String task, String date) {
        super(task, "event");
        String[] returnedArray = date.split(" ");
        if (returnedArray.length == 1) {
            this.date = LocalDate.parse(date);
        } else {
            this.date = LocalDate.parse(date,
                    DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        }
    }

    /**
     * Returns a String representation of an Event
     *
     * @return String representation of an Event
     */
    @Override
    public String toString() {
        return "[E] " + super.toString()
                + " (at: " + date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)) + ")";
    }
}
