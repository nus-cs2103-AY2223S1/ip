package wanya.task;

import wanya.parser.DateTimeParser;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime date;
    private final String TASK_TYPE = "E";

    public Event(String taskName, String date) throws DateTimeException {
        super(taskName);
        this.date = DateTimeParser.getDateTime(date);
    }

    public Event(String taskName, boolean hasCompleted, String date) {
        super(taskName, hasCompleted);
        this.date = DateTimeParser.getDateTime(date);
    }

    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString() + "(at: " + DateTimeParser.getDateTimeString(date) + ")";
    }

    @Override
    public String toStorageString() {
        return TASK_TYPE + "|" + super.toStorageString()
                + "|" + DateTimeParser.getDateTimeStorage(date);
    }
}
