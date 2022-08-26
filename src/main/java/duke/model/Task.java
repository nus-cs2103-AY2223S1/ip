package duke.model;

/**
 * A class representing the Task to be tracked.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    private static int numOfTasks;

    /**
     * The constructor for a Task.
     *
     * @param description the details of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the symbol representing the completion status of a task for printing on screen.
     *
     * @return "X" or an empty spacing depending on whether the task is marked as done.
     */
    protected String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the Task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the Task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the total number of tasks being tracked.
     *
     * @return the total number of tasks
     */
    public static int getNumOfTasks() {
        return numOfTasks;
    }

    /**
     * Increments the total number of tasks being tracked.
     */
    public static void incrementNumOfTasks() {
        numOfTasks += 1;
    }

    /**
     * Decrements the total number of tasks being tracked.
     */
    public static void decrementNumOfTasks() {
        numOfTasks -= 1;
    }

    /**
     * Returns a formatted string of a Task to be stored in the storage.
     *
     * @return a formatted string of a Task for storage
     */
    public String toStorage() {
        return "";
    }

    /**
     * Returns a string representation of a Task.
     *
     * @return a string representing a Task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
