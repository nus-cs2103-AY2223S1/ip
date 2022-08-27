package duke.task;

public abstract class Task {
    private String taskDescription;
    private boolean isDone;

    public Task(String description) {
        this.taskDescription = description;
        this.isDone = false;
    }

    /**
     * Marks this <code>Task</code> as done.
     */
    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:\n " + this);
    }

    /**
     * Marks this <code>Task</code> as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:\n " + this);
    }

    /**
     * Sets the status of this <code>Task</code>.
     *
     * @param isDone a boolean of whether the <code>Task</code> is done
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns 'X' if <code>Task</code> is done, else returns ' '.
     *
     * @return 'X' if <code>Task</code> is done, else ' '
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns true if and only if keyword in a substring of the <code>Task</code> description.
     *
     * @param keyword word being searched for
     * @return true if and only if keyword in a substring of the task description
     */
    public boolean contains(String keyword) {
        return taskDescription.contains(keyword);
    }

    /**
     * Returns this <code>Task</code> in CSV format.
     *
     * @return CSV representation of this <code>Task</code>
     */
    public String toCsv() {
        int status = this.isDone ? 1 : 0;
        return status + "," + this.taskDescription;
    }

    /**
     * Returns a string representation of this <code>Task</code>.
     *
     * @return a string representation of this <code>Task</code>
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.taskDescription;
    }
}
