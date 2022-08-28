package duke.task;

import java.time.LocalDateTime;

import duke.exception.UnexpectedDateTimeFormatException;
import duke.util.DateTimeHandler;

/**
 * Represents a Task with date and time.
 */
public abstract class ScheduleTask extends Task {
    private LocalDateTime dateTime;

    /**
     * Constructs ScheduleTask object with a description, date and time.
     *
     * @param description description of the task.
     * @param dateTime the deadline of the task.
     * @throws UnexpectedDateTimeFormatException when dateTime is not in dd/MM/YYYY HHmm format.
     */
    public ScheduleTask(String description, String dateTime) throws UnexpectedDateTimeFormatException {
        super(description);
        this.dateTime = DateTimeHandler.formatDukeDateTime(dateTime);
    }

    /**
     * Constructs ScheduleTask object with a description, date, time and done.
     *
     * @param description description of the task.
     * @param dateTime the deadline of the task.
     * @param done task done or not.
     */
    public ScheduleTask(String description, String dateTime, boolean done) {
        super(description);
        this.dateTime = DateTimeHandler.formatStorageDateTime(dateTime);
        isDone = done;
    }

    /**
     * Returns date and time in the format MMM dd yyyy HH:mm
     * @return date and time in the format MMM dd yyyy HH:mm
     */
    protected String showDateTime() {
        return dateTime.format(DateTimeHandler.storageDateTimeFormat);
    }
}
