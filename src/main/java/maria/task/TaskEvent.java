package maria.task;

import maria.util.DukeDateTimeFormatter;

import java.time.LocalDate;

public class TaskEvent extends Task{

    private LocalDate startTime;
    private LocalDate endTime;

    public TaskEvent(String name, boolean isDone, LocalDate startTime, LocalDate endTime)
            throws TaskNoNameException {
        super(name, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from " +
                DukeDateTimeFormatter.formatDisplay(this.startTime) + " to " +
                DukeDateTimeFormatter.formatDisplay(this.endTime) + ")";
    }

    @Override
    public String toStorageString() {
        return super.toStorageString() + "|||" + "event" + "|||" +
                DukeDateTimeFormatter.formatStorage(this.startTime) + "|||" +
                DukeDateTimeFormatter.formatStorage(this.endTime);
    }

}