/**
 * Class that encapsulates a task and tracks its completion status.
 */
public class Task {
    /**
     * Stores the description and completion status of the task.
     */
    private boolean done;
    private String name;

    /**
     * Constructor for a Task item.
     * @param name Description of task
     */
    Task(String name) {
        this.name = name;
        this.done = false;
    }

    /**
     * Retrieves the description of the Task.
     * @return Task description
     */
    public String getName() {
        return name;
    }

    /**
     * Checks if Task is completed
     * @return True if Task completed, False if not
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Changes the completion status of the Task.
     */
    public void toggleDone() {
        this.done = !this.done;
    }

    /**
     * Renames the Task to the input name.
     * @param s New name of task
     */
    public void renameTask(String s) {
        this.name = s;
    }
}
