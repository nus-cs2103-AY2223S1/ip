package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task. A Deadline object contains the description and deadline task is due by.
 */
public class Deadline extends TaskWithDate {

    /**
     * Constructor for Deadline
     * @param description String describing the deadline
     * @param by LocalDateTime object giving dateline of task
     */
    public Deadline(String description, LocalDateTime by) {
        super(description, by);
    }

    /**
     * @return String to save onto text document
     */
    @Override
    public String textFormat() {
        return "D|" + (isDone ? 1 : 0) + "|" + description + "|" + getTiming();
    }

    /**
     * @return String to be displayed to users
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateToString() + ")";
    }
}
