package wanya.task;

import wanya.parser.DateTimeParser;

import java.time.DateTimeException;
import java.time.LocalDateTime;

/**
 * Represents the DoWithinPeriod task.
 */
public class Period extends Task {
    private static final String TASK_TYPE = "P";
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    /**
     * Constructs a Period task given a task name and date in String.
     *
     * @param taskName description of the Period task.
     * @param startDate start date of the Period task.
     * @param endDate end date of the Period task.
     */
    public Period(String taskName, String startDate, String endDate)
            throws DateTimeException {
        super(taskName);
        this.startDate = DateTimeParser.getDateTime(startDate);
        this.endDate = DateTimeParser.getDateTime(endDate);
    }

    /**
     * Returns the String representation of the Period task.
     *
     * @return String representation of the Period task.
     */
    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString() + "(from: "
                + DateTimeParser.getDateTimeString(startDate) + " - "
                + DateTimeParser.getDateTimeString(endDate) + ")";
    }

    /**
     * Encodes a Period object to a String representation for storage.
     *
     * @return String representation of the Period task in storage.
     */
    @Override
    public String toStorageString() {
        return TASK_TYPE + "|" + super.toStorageString() + "|"
                + DateTimeParser.getDateTimeStorage(startDate) + "|"
                + DateTimeParser.getDateTimeStorage(endDate);
    }
}
