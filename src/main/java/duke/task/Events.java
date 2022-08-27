package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

/**
 * Represents an event task.
 */
public class Events extends Task {
    private final LocalDateTime timing;

    /**
     * Constructor for Events class.
     *
     * @param task task in String.
     * @param timing timing event occurs in String.
     * @param isDone whether task is done.
     *             true if marked.
     *             false if unmarked.
     *
     * @throws DukeException if timing is not in the format dd/MM/yyyy HHmm.
     */
    public Events(String task, String timing, boolean isDone) throws DukeException {
        super(task, isDone);
        this.timing = convertDateTime(timing);
    }

    /**
     * Returns a String representation of the event task.
     *
     * @return String representation of the event.
     */
    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "[E]" + super.toString() + String.format(" (at: %s)", timing.format(format));
    }

    /**
     * Returns a String representation of the event task in save file format.
     *
     * @return String representation of event task in save file format.
     */
    @Override
    public String toSaveString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return "event " + super.toSaveString() + " " + timing.format(format);
    }

    /**
     * Returns date and timing reformat as a LocalDateTime in MMM dd yyyy HHmm
     *
     * @param dateTime date and timing in String.
     * @return LocalDateTime in MMM dd yyyy HHmm
     * @throws DukeException if dateTime is not in format dd/MM/yyyy HHmm.
     */
    public LocalDateTime convertDateTime(String dateTime) throws DukeException {
        return Task.getLocalDateTime(dateTime);
    }
}
