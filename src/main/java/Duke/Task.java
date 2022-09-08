package Duke;

import java.time.LocalDate;

/**
 * Represents a task.
 */
public class Task implements Comparable<Task> {
    private String description;
    private boolean isDone;
    private LocalDate date;
    public Task(String description, LocalDate date) {
        this.description = description;
        this.isDone = false;
        this.date = date;
    }

    /**
     * Checks if a task is done.
     *
     * @return String indicating whether a task is completed.
     */
    public String getStatusIcon() {
        return (isDone? "X": " ");
    }
    public void markDone() {
        this.isDone = true;
    }
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of Task.
     *
     * @return String representation of Task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon()+ "] " + this.description;
    }

    /**
     * Allows comparing of Task deadline.
     *
     * @param o Task to be compared with.
     * @return An integer representing if a task should be arranged before another task.
     */
    @Override
    public int compareTo(Task o) {
        if(this.date == null && o.date == null) {
            return 0;
        } else if(this.date == null) {
            return -1;
        } else if(o.date == null) {
            return 1;
        } else {
            return this.date.compareTo(o.date);
        }

    }
}
