package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a Task in Duke.
 * 
 * @author Ramanathan Kumarappan
 */
public class Task implements Comparable<Task> {
    private String description;
    private Boolean isDone;

    /**
     * Constructor for Task.
     * 
     * @param description - The description of the Task.
     */
    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Mark the Task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Mark the Task as not done.
     */
    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the Task in a save friendly format.
     * 
     * @return The Task in a save string format.
     */
    public String saveString() {
        return (isDone ? 1 : 0) + " | " + description;
    }
    
    public boolean containsKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    /**
     * Get the date associated with this task.
     * 
     * @return null as not all Tasks have dates associated with them.
     */
    public LocalDate getDate() {
        return null;
    }

    /**
     * Get the time associated with this task.
     *
     * @return null as not all Tasks have time associated with them.
     */
    public LocalTime getTime() {
        return null;
    }
    
    @Override
    public int compareTo(Task task2) {
        if (this.getDate() == null && task2.getDate() == null) {
            return 0;
        }
        if (this.getDate() != null && task2.getDate() == null ) {
            return 1;
        } 
        if (this.getDate() == null && task2.getDate() != null) {
            return  -1;
        }
        if (this.getDate().compareTo(task2.getDate()) == 0) {
            if (this.getTime() == null && task2.getTime() == null) {
                return 0;
            }
            if (this.getTime() != null && task2.getTime() == null) {
                return 1;
            }
            if (this.getTime() == null && task2.getTime() != null) {
                return  -1;
            }
            return this.getTime().compareTo(task2.getTime());
        }
        return this.getDate().compareTo(task2.getDate());
    }

    /**
     * Returns a string representation of the Task.
     * @return The string representation of the Task.
     */
    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "]" + " " + this.description;
    }
}
