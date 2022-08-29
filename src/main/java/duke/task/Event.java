package duke.task;

import duke.exception.UnexpectedDateTimeFormatException;

/**
 * Represents a task to be done at a certain date and time.
 */
public class Event extends ScheduleTask {

    /**
     * Constructs Event object with a description, date and time.
     *
     * @param description description of task.
     * @param at date and time to complete the task at.
     * @throws UnexpectedDateTimeFormatException when date and time is not in the format dd/MM/yyyy HHmm.
     */
    public Event(String description, String at) throws UnexpectedDateTimeFormatException {
        super(description, at);
    }

    /**
     * Constructs Event object with a description, date, time and done.
     *
     * @param description description of task.
     * @param at date and time to complete the task at.
     * @param isDone task done or not.
     */
    public Event(String description, String at, boolean isDone) {
        super(description, at, isDone);
    }

    /**
     * Return the String representation of Event object.
     * @return String representation of Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + showDateTime() + ")";
    }
}
