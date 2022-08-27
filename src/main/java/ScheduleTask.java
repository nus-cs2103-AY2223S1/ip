import java.time.LocalDateTime;

public abstract class ScheduleTask extends Task {
    protected LocalDateTime dateTime;

    public ScheduleTask(String description, String dateTime) throws UnexpectedDateTimeFormatException {
        super(description);
        this.dateTime = DateTimeHandler.formatDukeDateTime(dateTime);
    }

    public ScheduleTask(String description, String dateTime, boolean done) {
        super(description);
        this.dateTime = DateTimeHandler.formatStorageDateTime(dateTime);
        isDone = done;
    }

    protected String showDateTime() {
        return dateTime.format(DateTimeHandler.storageDateTimeFormat);
    }
}
