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

    /**
     * Overloaded constructor for Event.
     *
     * @param taskDescription Description of Event.
     * @param startDateTime   Starting datetime for Event.
     * @param endDateTime     Ending datetime for Event.
     */
    public Event(String taskDescription, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(taskDescription);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Overloaded constructor for Event.
     *
     * @param taskDescription Description of Event.
     * @param isComplete      Whether or not the Event is complete.
     * @param startDateTime   Starting datetime for Event.
     * @param endDateTime     Ending datetime for Event.
     */
    public Event(String taskDescription, boolean isComplete, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(taskDescription);
        this.setIsComplete(isComplete);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Returns the encoded representation of the current Event to be saved to an external storage.
     *
     * @return String representation of current Event for saving to Storage.
     */
    @Override
    public String getEncodedValue() {
        String formattedStartDateTime = this.startDateTime.format(DateTimeFormatter.ofPattern("MMM dd uuuu, HHmm")
                .withResolverStyle(ResolverStyle.STRICT));
        String formattedEndDateTime = this.endDateTime.format(DateTimeFormatter.ofPattern("MMM dd uuuu, HHmm")
                .withResolverStyle(ResolverStyle.STRICT));
        return String.format("[E]#%s#%s#%s#%s",
                getTaskDescription(),
                getIsComplete(),
                formattedStartDateTime,
                formattedEndDateTime);
    }

    /**
     * Returns string representation of Event to be printed to terminal.
     *
     * @return Returns string representation of Event to be printed to terminal.
     */
    @Override
    public String toString() {
        String formattedStartDateTime = this.startDateTime.format(DateTimeFormatter.ofPattern("MMM dd uuuu, HHmm")
                .withResolverStyle(ResolverStyle.STRICT));
        String formattedEndDateTime = this.endDateTime.format(DateTimeFormatter.ofPattern("MMM dd uuuu, HHmm")
                .withResolverStyle(ResolverStyle.STRICT));
        return String.format("[E] %s (at: %s to %s)", super.toString(), formattedStartDateTime, formattedEndDateTime);
    }
}
