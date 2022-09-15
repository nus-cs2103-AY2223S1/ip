package chacha.tasks;

import java.time.LocalDateTime;

/**
 * Represents a task.
 */
public class Task {
    private String description;
    private boolean isDone;
    private String type;
    private LocalDateTime date = LocalDateTime.MAX;

    /**
     * Constructor for a task.
     * 
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "";
        this.date = null;
    }

    public Task() {
    }

    /**
     * Mark task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }
    
    /**
     * Unmark task as done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }
 
    /** 
     * Gets status icon of task.
     * 
     * @return X if done and empty string if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); 
    }
    
    /** 
     * Gets description of task.
     * 
     * @return Description of task.
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
     * Gets date of task.
     * 
     * @return Date of task.
     */
    public LocalDateTime getDate() {
        return date;
    }
     
}
