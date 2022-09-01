import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;
    /**
     * Construct Task with a fixed name.
     *
     * @param name The name of the task.
     */
    public Event(String name, LocalDateTime startTime, LocalDateTime endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Shows the event name and status as a String.
     *
     * @return A String with the task name and status.
     */
    public String toString() {
        return String.format("[E]%s (from %s to %s)", super.toString(),
            startTime.format(DateTimeFormatter.ofPattern("d MMM yyyy 'at' HH:mm:ss")),
            endTime.format(DateTimeFormatter.ofPattern("d MMM yyyy 'at' HH:mm:ss")));
    }
}
