package duke.model;

/**
 * A class representing the Task to be tracked.
 */
public class Task implements Cloneable {
    private static int numOfTasks;
    protected String description;
    protected boolean isDone;

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
     * Sets the total number of tasks being tracked.
     *
     * @param num the number to set as the total number of tasks
     */
    public static void setNumOfTasks(int num) {
        numOfTasks = num;
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
     * Returns if the task's description contains the input description.
     *
     * @param description The description to be compared with.
     * @return A boolean representing if the task's description contains the input description.
     */
    public boolean contains(String description) {
        return this.description.contains(description);
    }

    /**
     * Makes a shallow copy of this Task.
     *
     * @return A shallow copy of this Task.
     * @throws CloneNotSupportedException If a shallow copy could not be made.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Returns a formatted string of a Task to be stored in the storage.
     *
     * @return A formatted string of a Task for storage.
     */
    public String toStorage() {
        return "";
    }

    /**
     * Returns a string representation of a Task.
     *
     * @return A string representing a Task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
