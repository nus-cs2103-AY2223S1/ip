package dukechatbot.utility;
import java.time.LocalDateTime;

/**
 * The Task class implements the parent class of the tasks to be kept in the task list when the program is called.
 *
 * @author A0233290M
 * @version Week3
 */
public class Task {
    /**
     * The description associated with the instance of Task.
     */
    protected String description;
    /**
     * The boolean associated with the instance of the class to label whether the task is done or not.
     */
    protected boolean isDone;
    /**
     * Defines the time attribute associated with the instance of Task.
     */
    protected LocalDateTime time;

    /**
     * Constructs the instance of Task with the associated description. The Task class does not have
     * an associated time attribute and hence the instance has its time field left to be null.
     * @param description describes the instance of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.time = null;
    }

    /**
     * Answers whether the task is done or not.
     * @return a string that tells the caller whether the task is done or not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task done when called.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done when called.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String out = "[" + getStatusIcon() + "] " + this.description;
        return out;
    }
}
