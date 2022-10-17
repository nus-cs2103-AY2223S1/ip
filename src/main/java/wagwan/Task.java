package wagwan;

/**
* Task is a parent class that serves as a blueprint for all tasks a user can add to the to-do list.
*
* @author Linus Chui
*/
public class Task {

    /**
     * The description of the task.
     */
    protected String description;

    /**
     * The done boolean decides if a task has been completed.
     */
    protected boolean isDone;

    /**
     * Constructor for a task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * A getter method that checks if a task has been completed.
     *
     * @return "X" if task has been completed, " " otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * A setter method that marks an uncompleted task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * A setter method that marks a completed task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * A setter method to update the description of a task.
     */
    public void updateDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Shows the task's completion status and task description.
     *
     * @return Formats task's completion status and task description.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
