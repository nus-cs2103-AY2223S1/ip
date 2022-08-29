package duke.task;

//import time
import java.time.LocalDateTime;

//import exception
import duke.exception.UnexpectedDateTimeFormatException;

//import util
import duke.util.DateTimeHandler;

public abstract class ScheduleTask extends Task {
    private LocalDateTime dateTime;

    public ScheduleTask(String description, String dateTime) throws UnexpectedDateTimeFormatException {
        super(description);
        this.dateTime = DateTimeHandler.formatDukeDateTime(dateTime);
    }

    public ScheduleTask(String description, String dateTime, boolean isDone) {
        super(description);
        this.dateTime = DateTimeHandler.formatStorageDateTime(dateTime);
        this.isDone = isDone;
    }

    protected String showDateTime() {
        return dateTime.format(DateTimeHandler.storageDateTimeFormat);
    }
}
