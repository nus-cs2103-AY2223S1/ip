package wanya.task;

import wanya.parser.DateTimeParser;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Deadline extends Task{
    private LocalDateTime dueDate;
    private final String TASK_TYPE = "D";
    private final static DateTimeParser PARSER = new DateTimeParser();

    public Deadline(String taskName, String dueDate) throws DateTimeException {
        super(taskName);
        this.dueDate = PARSER.getDateTime(dueDate);
    }

    public Deadline(String taskName, boolean hasCompleted, String dueDate) {
        super(taskName, hasCompleted);
        this.dueDate = PARSER.getDateTime(dueDate);
    }


    @Override
    public String toString() {
        return "[" + TASK_TYPE +"]" + super.toString() + "(by: " + PARSER.getDateTimeString(dueDate) + ")";
    }

    @Override
    public String toStorageString() {
        return TASK_TYPE + "|" + super.toStorageString() + "|" + PARSER.getDateTimeStorage(dueDate);
    }
}
