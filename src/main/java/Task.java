/**
 * Class Task
 *
 * Description: An encapsulation of what a completable task is in the context of Duke.
 * Has description and isDone field to describe and mark completion status of a task.
 *
 * @author Yuvaraj Kumaresan
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor
     *
     * @param description The string describing the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Method getStatusIcon
     *
     * @return String representation of the isDone field.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Method setIsDone
     *
     * @param done Boolean value depicting if the task is done or not done.
     */
    public void setIsDone(Boolean done) {
        this.isDone = done;
    }

    /**
     * Method getIsDone
     *
     * @return Boolean representing if the task is done.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Method getDescription
     *
     * @return String with the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Method toString()
     *
     * @return String representation of the task object.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
