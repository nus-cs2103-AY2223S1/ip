import java.time.format.DateTimeFormatter;

/**
 * Represents a Task, which can be marked done/not done, along with some description
 */
public class Task {
    public static final String DATE_TIME_INPUT_FORMAT = "yyyy-MM-dd HH:mm";
    
    public static final DateTimeFormatter dateTimeParser = DateTimeFormatter.ofPattern(DATE_TIME_INPUT_FORMAT);
    public static final DateTimeFormatter dateTimeDisplayFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    protected final String description;
    protected boolean isDone;

    /**
     * Constructor for task that takes in some description to identify the task
     *
     * @param description The specified description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the current task done
     */
    public void markTask() {
        this.isDone = true;
    }

    /**
     * Marks the current task not done
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    /**
     * Returns whether the task is done / not done, tasks that are done will be marked with "X"
     */
    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
