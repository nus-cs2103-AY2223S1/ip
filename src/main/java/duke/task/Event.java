package duke.task;

import java.time.LocalDate;

import duke.Parser;
import duke.exception.DukeException;

/**
 * Class containing information regarding
 * task of type Event.
 *
 * @author Elbert Benedict
 */
public class Event extends Task {
    public static final String TYPE_SYMBOL = "[E]";
    private LocalDate date;

    /**
     * Constructs a new Event instance.
     *
     * @param task the description of the event.
     * @param date the date which the event is being held.
     * @throws DukeException If date is not a valid date.
     */
    public Event(String task, String date) throws DukeException {
        super(task);
        this.date = Parser.convertToDateObj(date);
    }
    /**
     * Constructs a new Event instance.
     *
     * @param task the description of the event.
     * @param date the date which the event is being held.
     * @param isDone whether the event has been marked as done.
     * @throws DukeException If date is not a valid date.
     */
    public Event(String task, String date, boolean isDone, String priority) throws DukeException {
        super(task, isDone, priority);
        this.date = Parser.convertToDateObj(date);
    }

    /**
     * Returns the string representation of the
     * Event instance.
     *
     * @return string representaion of the Event instance.
     */
    @Override
    public String toString() {
        return TYPE_SYMBOL + super.toString()
                + " (at: " + Parser.printDate(date) + ")";
    }

    /**
     * Returns the string representation for the Event instance
     * for the save file.
     *
     * @return the string representation for the Event instance
     *     for the save file.
     */
    @Override
    public String toSaveFileString() {
        return TYPE_SYMBOL + " @ " + getStatusIcon() + " @ " + super.getPriority()
               + " @ " + super.getTask() + " @ " + Parser.printSaveFileDate(date);
    }
}
