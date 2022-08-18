/**
 * Task class that contains information and methods about a task
 *
 * @author Rexong [copied some from the cs2103 website]
 */

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for task setting the description to value passed in as parameter
     * and setting isDone to false.
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get status Icon.
     * @return X if task is done else whitespace
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Mark isDone as true.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Mark isDone as false.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Description is the string representation of task
     * @return description
     */
    @Override
    public String toString() {
        return description;
    }

}
