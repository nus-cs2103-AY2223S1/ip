public class Task {

    private boolean isDone;
    private String taskDescription;
    private int index;
    private String straightLine = "  ----------------------------------------------------------------------------------";

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
     * A method to return if the task is done or not.
     *
     * @return The boolean to indicate if task is done.
     */
    public boolean getStatus() {
        return isDone;
    }

    /**
     * A method to return the task description.
     *
     * @return A String description of the task.
     */
    public String getDescription() {
        return taskDescription;
    }

    /**
     * A method to return integer index of the task in the list of tasks.
     *
     * @return The integer index of the task in list of tasks.
     */
    public int getIndex() {
        return index;
    }

    /**
     * A method to change the task from undone to done. Outputs in the console
     * details of the task done to inform the user about the change.
     */
    public void markDone() {

        this.isDone = true;

        System.out.println(straightLine + "\n  Good Job! You're Killing It!\n  [X] " + taskDescription
                + "\n" + straightLine + "\n");
    }

    /**
     * A method to change the task from done to undone. Outputs in the console
     * details of the task undone to inform user about the change.
     */
    public void markUndone() {

        this.isDone = false;

        System.out.println(straightLine + "\n  AAaaa please get it done soon...\n  [ ] " + taskDescription
                + "\n" + straightLine + "\n");
    }

    /**
     * A method to update the index of the task in the list of tasks.
     *
     * @param newIndex The most current integer index of the task.
     */
    public void setIndex(int newIndex) {

        this.index = newIndex;

    }

    /**
     * A method to output in the console the task added to the list of tasks.
     */
    public void printAdded() {
        System.out.println(straightLine + "\n added: " + taskDescription + "\n" + straightLine + "\n\n");
    }

    /**
     * A method to output in the console the full details of the task.
     */
    public void printTask() {
        if (!isDone) {
            System.out.println("  " + index + ".[ ] " + taskDescription);
        } else {
            System.out.println("  " + index + ".[X] " + taskDescription);
        }

    }

    /**
     * A method to output in the console the task deleted from the list of tasks.
     */
    public void printDeleted() {
        System.out.println(straightLine + "\n  Task deleted!\n" + straightLine + "\n");
    }

}
