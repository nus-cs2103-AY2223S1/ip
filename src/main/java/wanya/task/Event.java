package wanya.task;

import wanya.parser.DateTimeParser;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime date;
    private final String TASK_TYPE = "E";
    private final static DateTimeParser PARSER = new DateTimeParser();

    public Event(String taskName, String date) throws DateTimeException {
        super(taskName);
        this.date = PARSER.getDateTime(date);
    }

    public Event(String taskName, boolean hasCompleted, String date) {
        super(taskName, hasCompleted);
        this.date = PARSER.getDateTime(date);
    }

    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString() + "(at: " + PARSER.getDateTimeString(date) + ")";
    }

    @Override
    public String toStorageString() {
        return TASK_TYPE + "|" + super.toStorageString()
                + "|" + PARSER.getDateTimeStorage(date);
    }
}
