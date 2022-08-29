package duke.task;

//import exception
import duke.exception.TaskMarkException;
import duke.exception.TaskUnmarkException;

/**
 * Represents a Task object by its description and whether it is done or not.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object.
     * Description is given and isDone is false by default.
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task object.
     * Both description and done is given.
     *
     * @param description the description of the task.
     * @param isDone whether the task is done or not.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks task as done.
     *
     * @throws TaskMarkException when task is already done.
     */
    public void mark() throws TaskMarkException{
        if (isDone) {
            throw new TaskMarkException();
        }
        isDone = true;
    }

    /**
     * Marks task as not done.
     *
     * @throws TaskUnmarkException when task is not done yet.
     */
    public void unmark() throws TaskUnmarkException{
        if (!isDone) {
            throw new TaskUnmarkException();
        }
        isDone = false;
    }

    /**
     * Returns whether the task is done or not.
     *
     * @return true if done else false.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns a String Representation of a task.
     *
     * @return String Representation of a task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
