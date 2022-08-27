package duke.task;

import java.time.LocalDate;

import duke.parser.DateParser;

/**
 * Deadline task.
 */
public class Deadline extends Task {
    private LocalDate date;

    /**
     * Constructor for <code>Deadline</code>.
     * @param description
     * @param date
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * String format of Deadline.
     * @return
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateParser.convertDateToString(date) + ")";
    }

    /**
     * Convert a Deadline to a String to store with Storage.
     * @return
     */
    @Override
    public String toMemoryString() {
        return "D | " + super.toMemoryString() + " | " + DateParser.convertDateToString(date);
    }
}
