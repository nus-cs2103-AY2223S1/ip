package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.common.DukeException;

/**
 * Represents an event-type task.
 *
 * @author Rama Aryasuta Pangestu
 */
public class Event extends Task {
    private final LocalDate localDate;

    /**
     * Constructs an event task.
     *
     * @param description the description of the task
     * @param isDone      denotes whether the task is marked done or not
     * @param localDate   the date of the event
     * @throws DukeException if the description of the task is empty
     */
    public Event(String description, boolean isDone, LocalDate localDate) throws DukeException {
        super("event", description, isDone);
        this.localDate = localDate;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return super.toString() + " (at: "
                + this.localDate.format(DateTimeFormatter.ofPattern("MMM d, yyyy")) + ")";
    }

    /**
     * Returns a string encoding the event task for saving the task in the save file.
     *
     * @return the string encoding
     */
    @Override
    public String encode() {
        return super.encode() + Task.ENCODING_SEPARATOR
                + this.localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
