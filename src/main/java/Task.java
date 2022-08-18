/**
 * This class is used to simulate the individual task entered by the
 * user and encapsulates the task description as well as task status
 * within its fields.
 */
public class Task {
    //To encapsulate the information regarding the task
    protected String description;
    //To keep track of the status of the task (done/not done)
    protected boolean isDone;

    /**
     * This is a constructor for Task. It creates an instance of Task and sets
     * the fields of the Task instance.
     *
     * @param description This is a String describing the task to be done.
     * @return an instance of Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * This method is used to check the status of the task (done/undone)
     * and mark it with an X if it is done.
     *
     * @return a String representation of "X" for tasks that are done and
     * an empty space " " for tasks that are undone.
    */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * This method is used to mark the field isDone for each task as done (true).
     */
    public void markTaskDone() {
        isDone = true;
    }

    /**
     * This method is used to mark the field isDone for each task as undone (false).
     */
    public void markTaskUndone() {
        isDone = false;
    }

    /**
     * This method provides a string representation of the task status and task
     * description as follows "[task status] task_description".
     *
     * @return a String representation of the formatted task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
