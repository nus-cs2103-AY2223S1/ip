package duke;

public class Task {

    private boolean isDone;
    private String taskDescription;
    private int index;
    private String tag;

    /**
     * A constructor to initialize a task object.
     *
     * @param isDone A boolean to indicate if the task is completed.
     * @param taskDescription A string to detail the task.
     * @param index A integer to indicate position of the task in list of tasks.
     * @param tag A string to describe event in one word.
     */
    public Task(boolean isDone, String taskDescription, int index, String tag) {
        this.isDone = isDone;
        this.taskDescription = taskDescription;
        this.index = index;
        this.tag = tag;
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
     * @return a String description of the task.
     */
    public String getDescription() {
        assert taskDescription != null && taskDescription != "";

        return taskDescription;
    }

    /**
     * Returns integer index of the task in the list of tasks.
     *
     * @return The integer index of the task in list of tasks.
     */
    public int getIndex() {
        assert index > 0;
        return index;
    }

    public String getTag() {
        return tag;
    }

    /**
     * Changes the task from undone to done.
     *
     * @return a String representation on details of the task done.
     */
    public String markDone() {

        this.isDone = true;

        return "\n  Good Job! You're Killing It!\n  [X] " + taskDescription;
    }

    /**
     * Changes the task from done to undone.
     *
     * @return a String representation on details of the task undone.
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
     * Returns a string describing the task added to the list of tasks.
     *
     * @return a String representation of task added.
     */
    public String printAdded() {
        return "\n added: " + taskDescription + "\n";
    }

    /**
     * Returns a string on the full details of the task.
     *
     * @return a String representation of task details.
     */
    public String printTask() {
        if (!isDone) {
            return "  " + index + ".[ ] " + taskDescription;
        } else {
            return "  " + index + ".[X] " + taskDescription;
        }

    }

    /**
     * Returns a string representation of the Task.
     *
     * @return a String describing the task.
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
     * @return a String representation of task to be saved in the hard disk.
     */
    public String toSavedString() {
        if (!isDone) {
            return "GN<" + taskDescription + ">";
        } else {
            return "GY<" + taskDescription + ">";
        }
    }

    /**
     * Returns a string describing the task deleted from the list of tasks.
     *
     * @return a String representation of the task deleted.
     */
    public String printDeleted() {
        return "\n  duke.Task deleted!\n";
    }

    public String tag(String hashtag) {
        this.tag = hashtag;

        return "Your tag " + hashtag + " has been added to the task :)";
    }
}
