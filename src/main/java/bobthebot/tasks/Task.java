package bobthebot.tasks;

/**
 * Class representing tasks.
 * */
public class Task {
    protected boolean isDone;
    protected String taskName;

    /**
     * Constructor for tasks.
     * @param name of the deadline.
     * */
    public Task(String name) {
        this.isDone = false;
        this.taskName = name.trim();
    }

    /**
     * Method to mark a task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Method to mark a task as undone.
     * */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Method to get the status icon of a task representing whether a task is done or not.
     * @return String representing the status icon
     * */
    public String getStatusIcon() {
        return (isDone ? "✔" : " ");
    }

    /**
     * Method to put the task in a format for storage
     * @return String representing the storage format of the task
     * */
    public String toStorageFormat() {
        int done = this.isDone ? 1 : 0;
        String res = String.format("error | %d | %s", done, taskName);
        return res;
    }

    /**
     * Method to return a string representation of a task.
     * @return String representation of task.
     * */
    @Override
    public String toString() {
        String res = "[" + getStatusIcon() + "] " + this.taskName;
        return res;
    }
}
