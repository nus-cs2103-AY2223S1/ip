package chacha.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private String description;
    private LocalDateTime date;
    private boolean isDone;
    private String type;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /**
     * Constructs an event.
     * 
     * @param description Description of the event.
     * @param date Date of the event.
     */
    public Event(String description, LocalDateTime date) {
        this.description = description;
        this.date = date;
        this.type = "E";
    }

    /**
     * Marks event as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks event as done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /** 
     * Gets status icon of event.
     * 
     * @return X if done and empty string if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /** 
     * Gets description of event.
     * 
     * @return Description of event.
     */
    public String getDescription() {
        return description;
    }

    /** 
     * Gets type of task.
     * 
     * @return Type of task.
     */
    public String getType() {
        return type;
    }

    /** 
     * Gets date of event.
     * 
     * @return Date of event.
     */
    public LocalDateTime getDate() {
        return date;
    }

    /** 
     * Formats event into a readable string.
     * 
     * @return Event as a string.
     */
    @Override
    public String toString() {
        return "[" + type + "]" + "[" + getStatusIcon() + "] " + description + " (at: " + date.format(formatter) + ")";
    }
     
}
