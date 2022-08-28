package bobthebot.tasks;

/**
 * Abstract class Task that represents tasks.
 * */
public abstract class Task {
    public boolean isDone;
    public String taskName;

    /**
     * Constructor for task instance.
     * @param name of the task.
     * */
    public Task(String name) {
        this.isDone = false;
        this.taskName = name.trim();
    }

    /**
     * Method to mark a task as done.
     * */
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
     * Method to get the status icon of the task.
     * */
    public String getStatusIcon() {
        return (isDone ? "✔" : " ");
    }

    /**
     * Abstract to put the task in a format for storage.
     * @return String which represents the format for storage of a task
     * */
    public abstract String toStorageFormat();

    /**
     * Method which returns a string representation of the task.
     * @return string representation of task
     * */
    @Override
    public String toString() {
        String res = "[" + getStatusIcon() + "] " + this.taskName;
        return res;
    }
}
