package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

/**
 * Represents a deadline task.
 */
public class Deadlines extends Task {
    private final LocalDateTime deadline;

    /**
     * Constructor for Deadlines class.
     *
     * @param task task in String.
     * @param deadline deadline task is due by in String.
     * @param isDone whether task is done.
     *             true if marked.
     *             false if unmarked.
     *
     * @throws DukeException if deadline is not in the format dd/MM/yyyy HHmm.
     */
    public Deadlines(String task, String deadline, boolean isDone) throws DukeException {
        super(task, isDone);
        this.deadline = convertDateTime(deadline);
    }

    /**
     * Returns a String representation of the deadline task.
     *
     * @return String representation of the deadline.
     */
    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "[D]" + super.toString() + String.format(" (by: %s)", deadline.format(format));
    }

    /**
     * Returns a String representation of the deadline task in save file format.
     *
     * @return String representation of the deadline task in save file format.
     */
    @Override
    public String toSaveString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return "deadline " + super.toSaveString() + " " + deadline.format(format);
    }

    /**
     * Returns date and timing reformat as a LocalDateTime in MMM dd yyyy HHmm
     *
     * @param dateTime date and timing in String.
     * @return LocalDateTime in MMM dd yyyy HHmm
     * @throws DukeException if dateTime is not in format dd/MM/yyyy HHmm.
     */
    private LocalDateTime convertDateTime(String dateTime) throws DukeException {
        return Task.getLocalDateTime(dateTime);
    }
}
