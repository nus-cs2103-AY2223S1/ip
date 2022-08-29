package duke.task;

//import exception
import duke.exception.UnexpectedDateTimeFormatException;

/**
 * Represents a task to be done by a certain date and time.
 */
public class Deadline extends ScheduleTask {

    /**
     * Constructs Deadline object with a description, date and time.
     *
     * @param description description of task.
     * @param by date and time to complete the task by.
     * @throws UnexpectedDateTimeFormatException when date and time is not in the format dd/MM/yyyy HHmm.
     */
    public Deadline(String description, String by) throws UnexpectedDateTimeFormatException {
        super(description, by);
    }

    /**
     * Constructs Deadline object with a description, date, time and done.
     *
     * @param description description of task.
     * @param by date and time to complete the task by.
     * @param isDone task is done or not.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, by, isDone);
    }

    /**
     * Returns a String representation of Deadline object.
     *
     * @return String representation of Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + showDateTime() + ")";
    }
    
}
