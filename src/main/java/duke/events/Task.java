package duke.events;

/**
 * <p>Task class is the parent class of the ToDo, Deadline and Event classes.</p>
 * <p>This class is used to create a task.</p>
 * <p>This class is a concrete class because it has an implementation.</p>
 */
public class Task {

    private String taskName;
    private boolean isDone = false;

    /**
     * Constructor for Task.
     * @param taskName the name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Method that marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Method that marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Method that returns a boolean showing whether the task is done or not.
     * @return a boolean showing whether the task is done or not.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Method that returns the name and completion status of the task.
     * @return the name and completion status of the task.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + taskName;
        } else {
            return "[ ] " + taskName;
        }
    }

    /**
     * Method that returns the name and completion status of the task in a special format for creating a save file.
     * @return the name and completion status of the task.
     */
    public String getSaveData() {
        return taskName + " | " + isDone;
    }

    public String getName() {
        return taskName;
    }
}
