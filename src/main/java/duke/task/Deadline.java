package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * A deadline task
 *
 * @author Pontakorn Prasertsuk
 */
public class Deadline extends Task {

    public static final String SYMBOL = "D";

    private final LocalDate date;

    /**
     * Constructs a new Deadline instance
     *
     * @param title the name of the task
     * @param status whether the task is completed or not
     * @param date the deadline of the task
     */
    public Deadline(String title, boolean status, String date) throws DateTimeParseException {
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
        return (SYMBOL + " | " + (this.status ? "1" : "0") + " | " + this.title + " | "
                + this.date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + ")";
    }
}
