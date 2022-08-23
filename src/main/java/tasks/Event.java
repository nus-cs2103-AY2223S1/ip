package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * Tasks.Event represents a Tasks.Task with a start and end datetime. These are represented as
 * LocalDateTime objects.
 * Inherits from Tasks.Task.
 */
public class Event extends Task {
    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;

    public Event(String taskDescription, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(taskDescription);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public Event(String taskDescription, boolean isComplete, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(taskDescription);
        this.setIsComplete(isComplete);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String getEncodedValue() {
        String formattedStartDateTime = this.startDateTime.format(DateTimeFormatter.ofPattern("MMM dd uuuu, HHmm").withResolverStyle(ResolverStyle.STRICT));
        String formattedEndDateTime = this.endDateTime.format(DateTimeFormatter.ofPattern("MMM dd uuuu, HHmm").withResolverStyle(ResolverStyle.STRICT));
        return String.format("[E]#%s#%s#%s#%s", getTaskDescription(), getIsComplete(), formattedStartDateTime, formattedEndDateTime);
    }

    @Override
    public String toString() {
        String formattedStartDateTime = this.startDateTime.format(DateTimeFormatter.ofPattern("MMM dd uuuu, HHmm").withResolverStyle(ResolverStyle.STRICT));
        String formattedEndDateTime = this.endDateTime.format(DateTimeFormatter.ofPattern("MMM dd uuuu, HHmm").withResolverStyle(ResolverStyle.STRICT));
        return String.format("[E] %s (at: %s to %s)", super.toString(), formattedStartDateTime, formattedEndDateTime);
    }
}
