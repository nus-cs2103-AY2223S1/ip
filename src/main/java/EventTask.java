import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a time-sensitive task that has a time period
 * during which it will occur.
 */
public class EventTask extends Task{

    /** The start time for the event. */
    private LocalDateTime startTime = null;

    /** The end time for the event. */
    private LocalDateTime endTime = null;

    /**
     * Constructs the event task given the description, starting time and
     * ending time.
     * 
     * @param description A description of the event.
     * @param startTime The start time for the event.
     * @param endTime The end time for the event.
     */
    public EventTask(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description, TaskType.EVENT);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Constructs the event task given the description, starting time and
     * ending time.
     * 
     * @param description A description of the event.
     * @param startTime The start time for the event.
     * @param endTime The end time for the event.
     * @param isMarked The completion status of the task.
     */
    public EventTask(String description, LocalDateTime startTime, LocalDateTime endTime, boolean isMarked) {
        super(description, TaskType.EVENT, isMarked);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns tthe string representation of the event.
     * @return String representation of the event
     */
    @Override
    public String toString() {
        String startTimeString = this.startTime.format(DateTimeFormatter.ofPattern("d/MMM/yy HH:mm"));
        String endTimeString = this.endTime.format(DateTimeFormatter.ofPattern("d/MMM/yy HH:mm"));
        return String.format("%s (at: %s to %s)", super.toString(), startTimeString, endTimeString);
    }

    @Override
    public String encodeForStorage() {
        return String.format("%s|%s|%s", 
                super.encodeForStorage(), 
                this.startTime.toString(), 
                this.endTime.toString());
    }
}
