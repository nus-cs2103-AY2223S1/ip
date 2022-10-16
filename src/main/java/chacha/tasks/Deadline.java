package chacha.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private String description;
    private LocalDateTime date;
    private boolean isDone;
    private String type;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /**
     * Constructs a deadline.
     * 
     * @param description Description of the deadline.
     * @param date Date of the deadline.
     */
    public Deadline(String description, LocalDateTime date) {
        this.description = description;
        this.date = date;
        this.type = "D";
    }

    /**
     * Marks deadline as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks deadline as done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /** 
     * Gets status icon of deadline.
     * 
     * @return X if done and empty string if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    } 
    
    /** 
     * Gets description of deadline.
     * 
     * @return Description of deadline.
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
     * Gets date of deadline.
     * 
     * @return Date of deadline.
     */
    public LocalDateTime getDate() {
        return date;
    }
    
    /** 
     * Formats deadline into a readable string.
     * 
     * @return Deadline as a string.
     */
    @Override
    public String toString() {
        return "[" + type + "]" + "[" + getStatusIcon() + "] " + description + " (by: " + date.format(formatter) + ")";
    }
     
}
