package duke.task;

import duke.tools.DateTime;

/**
 * Task in the task management.
 */
public class Task {
    private String task;
    private boolean isDone;

    /**
     * Creates a Task object.
     * @param task Short description of the task.
     */
    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    /**
     * Marks a task as completed.
     * @return The new completion status of the task: true.
     */
    public boolean markAsDone() {
        isDone = true;
        return true;
    }

    /**
     * Marks a task as not completed.
     * @return The new completion status of the task: false.
     */
    public boolean markAsNotDone() {
        isDone = false;
        return false;
    }

    /**
     * {@inheritdocs}
     * @return {@inheritdocs}
     */
    @Override
    public String toString() {
        if (isDone()) {
            return " | X | " + getTask();
        } else {
            return " |   |  " + getTask();
        }
    }

    /**
     * Returns true if the description is contained in the task desc, false otherwise.
     * @param desc Description searching for.
     * @return True if desc can be found, false otherwise.
     */
    public boolean contains(String desc) {
        return getTask().contains(desc);
    }

    public DateTime getParsedDate() {
        return new DateTime("9999-12-31");
    }

    public String getTask() {
        return task;
    }

    public boolean isDone() {
        return isDone;
    }
}
