package duke;

/**
 * Represents a task written by the user.
 */
public class Task {
    protected String name;
    protected boolean isDone;

    /**
     * Creates a Task object.
     * @param name Name of the task.
     * @param isDone Status of whether task is marked as done or not.
     */
    Task(String name, boolean isDone){
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Gets status icon to represent if a task is marked done. If yes, marked as [X], else [].
     * @return [X] if task is marked done, [] if task is not marked done.
     */
    public String getStatusIcon() {
        return(this.isDone? "[X] " : "[] " );
    }

    /**
     * Marks task as done.
     *  @return A string that would be outputted to the screen when marked task.
     */

    public String mark() {
        this.isDone = true;
        Ui ui = new Ui();
        return ui.markHelperUi(this);
    }

    /**
     * Marks task as undone.
     * @return A string that would be outputted to the screen when unmarked task.
     */
    public String unmark() {
        this.isDone = false;
        Ui ui = new Ui();
       return ui.unmarkHelperUi(this);
    }

    /**
     * Gets status of task that includes whether it is done and its name.
     * @return A string of task's status icon and its name.
     */
    public String getStatus() {
        return this.getStatusIcon() + "" + this.name;
    }

}
