package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task
 */
public class Deadline extends Task {
    private LocalDate date;

    /**
     * Constructs a deadline task
     *
     * @param task_description Description of deadline task
     * @param date Date of deadline task
     */
    public Deadline(String task_description, LocalDate date) {
        super(task_description);
        this.date = date;
    }

    /**
     * Constructs a deadline task
     *
     * @param task_description Description of deadline task
     * @param done Status of deadline task
     * @param date Date of deadline task
     */
    public Deadline(String task_description, boolean done, LocalDate date) {
        super(task_description, done);
        this.date = date;
    }

    /**
     * Returns deadline task
     *
     * @return deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date.format(DateTimeFormatter.
                ofPattern("MMM dd yyyy")) + ")";}
}
