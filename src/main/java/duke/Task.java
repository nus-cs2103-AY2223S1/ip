package duke;

public class Task {

    private boolean isDone;
    private String taskDescription;
    private int index;

    /**
     * A constructor to initialize a task object.
     *
     * @param isDone A boolean to indicate if the task is completed.
     * @param taskDescription A string to detail the task.
     * @param index A integer to indicate position of the task in list of tasks.
     */
    public Task(boolean isDone, String taskDescription, int index) {
        this.isDone = isDone;
        this.taskDescription = taskDescription;
        this.index = index;
    }

    /**
     * Returns if the task is done or not.
     *
     * @return The boolean to indicate if task is done.
     */
    public boolean getStatus() {
        return isDone;
    }

    /**
     * Returns the task description.
     *
     * @return A String description of the task.
     */
    public String getDescription() {
        return taskDescription;
    }

    /**
     * Returns integer index of the task in the list of tasks.
     *
     * @return The integer index of the task in list of tasks.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Changes the task from undone to done. Output in the console
     * details of the task done to inform the user about the change.
     */
    public String markDone() {

        this.isDone = true;

        return "\n  Good Job! You're Killing It!\n  [X] " + taskDescription;
    }

    /**
     * Changes the task from done to undone. Output in the console
     * details of the task undone to inform user about the change.
     */
    public String markUndone() {

        this.isDone = false;

        return "\n  AAaaa please get it done soon...\n  [ ] " + taskDescription;
    }

    /**
     * Updates the index of the task in the list of tasks.
     *
     * @param newIndex The most current integer index of the task.
     */
    public void setIndex(int newIndex) {

        this.index = newIndex;

    }

    /**
     * Outputs in the console the task added to the list of tasks.
     */
    public String printAdded() {
        return "\n added: " + taskDescription + "\n";
    }

    /**
     * Outputs in the console the full details of the task.
     */
    public String printTask() {
        if (!isDone) {
            return "  " + index + ".[ ] " + taskDescription;
        } else {
            return "  " + index + ".[X] " + taskDescription;
        }

    }

    /**
     * Returns a string representation of the duke.Task.
     *
     * @return string describing the task.
     */
    public String toString() {
        if (!isDone) {
            return "  " + index + ".[ ] " + taskDescription;
        } else {
            return "  " + index + ".[X] " + taskDescription;
        }
    }

    /**
     * Returns a string to be saved in the hard disk.
     *
     * @return A string representation of task to be saved in the hard disk.
     */
    public String toSavedString() {
        if (!isDone) {
            return "GN<" + taskDescription + ">";
        } else {
            return "GY<" + taskDescription + ">";
        }
    }

    /**
     * Outputs in the console the task deleted from the list of tasks.
     */
    public String printDeleted() {
        return "\n  duke.Task deleted!\n";
    }

}
