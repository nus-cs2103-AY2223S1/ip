package duke.task;

/**
 * Represents a general Task.
 */
public class Task implements Comparable<Task> {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task according to whether or not it is done.
     *
     * @param isDone The status of completion of the task.
     */
    public void mark(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String statusIcon = isDone ? "[X]" : "[ ]";
        return statusIcon + " " + description;
    }

    /**
     * Checks if the task description contains the specified keyword.
     *
     * @param keyword The keyword to check against.
     * @return Whether or not the task description contains the keyword.
     */
    public boolean containsKeyword(String keyword) {
        return description.contains(keyword);
    }

    /**
     * {@inheritDoc}
     * @param task The task to be compared to.
     */
    @Override
    public int compareTo(Task task) {
        return this instanceof DeadlineTask
                ? this.compareTo(task)
                : task instanceof DeadlineTask
                ? 1
                : 0;
    }
}
