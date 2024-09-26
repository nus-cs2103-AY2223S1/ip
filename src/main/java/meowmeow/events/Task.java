package meowmeow.events;

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
     *
     * @param taskName the name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return a boolean showing whether the task is done or not.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the name and completion status of the task as a String.
     *
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
     * Returns the name and completion status of the task in a special format for creating a save file.
     *
     * @return the name and completion status of the task.
     */
    public String getSaveData() {
        return taskName + " | " + isDone;
    }

    public String getName() {
        return taskName;
    }
}
