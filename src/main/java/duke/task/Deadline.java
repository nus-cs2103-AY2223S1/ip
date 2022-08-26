package duke.task;

import duke.parser.DateParser;

import java.time.LocalDate;

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
