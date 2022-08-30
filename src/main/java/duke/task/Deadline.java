package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task
 */
public class Deadline extends Task {
    private LocalDate date;

    /**
     * Constructs a deadline task.
     *
     * @param taskDescription Description of deadline task.
     * @param date Date of deadline task.
     */
    public Deadline(String taskDescription, LocalDate date) {
        super(taskDescription);
        this.date = date;
    }

    /**
     * Constructs a deadline task.
     *
     * @param taskDescription Description of deadline task.
     * @param done Status of deadline task.
     * @param date Date of deadline task.
     */
    public Deadline(String taskDescription, boolean done, LocalDate date) {
        super(taskDescription, done);
        this.date = date;
    }

    /**
     * Returns deadline task.
     *
     * @return deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date.format(DateTimeFormatter
                .ofPattern("MMM dd yyyy")) + ")";
    }
}
