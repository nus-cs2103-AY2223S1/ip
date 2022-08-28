package duke.task;

/**
 * Task in the task management.
 */
public class Task {
    private String task;
    private boolean done;

    /**
     * Creates a Task object.
     * @param task Short description of the task.
     */
    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    /**
     * Marks a task as completed.
     * @return The new completion status of the task: true.
     */
    public boolean done() {
        done = true;
        return true;
    }

    /**
     * Marks a task as not completed.
     * @return The new completion status of the task: false.
     */
    public boolean notDone(){
        done = false;
        return false;
    }

    /**
     * {@inheritdocs}
     * @return {@inheritdocs}
     */
    @Override
    public String toString(){
        if (done) {
            return " | X | " + task;
        } else {
            return " |   |  " + task;
        }
    }
}
