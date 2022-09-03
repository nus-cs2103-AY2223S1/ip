import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime eventTiming;

    public Event(String description, LocalDateTime eventTiming) {
        super(description);
        this.eventTiming = eventTiming;
        this.taskType = TaskType.EVENT;
    }

    @Override
    public String encode() {
        return super.encode() + " | " + this.eventTiming;
    }

    @Override
    public String toString() {
        return  super.toString() + " (at: " + eventTiming.format(Task.DATE_TIME_DISPLAY_FORMATTER) + ")";
    }
}
