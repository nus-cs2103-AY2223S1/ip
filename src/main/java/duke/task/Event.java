package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exception.DukeException;

/**
 * Represents an Event Task.
 */
public class Event extends Task {
    private LocalDate at;

    /**
     * Constructs a deadline task with task description and deadline date.
     *
     * @param description description of the task
     * @param at date of the task
     * @throws DukeException if date is in incorrect format
     */
    public Event(String description, LocalDate at) throws DukeException {
        super(description);
        this.at = at;
    }

    /**
     * Returns this Event Task in CSV format.
     *
     * @return CSV representation of this Event Task
     */
    @Override
    public String toCsv() {
        return "E," + super.toCsv() + "," + this.at + "\n";
    }

    /**
     * Returns a string representation of this Event Task.
     *
     * @return a string representation of this Event Task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: " + this.at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
