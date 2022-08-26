package anya.task;

public abstract class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Gets the status icon of the task which depends on whether the task is done.
     *
     * @return X if the task is done; an empty string otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns true if the task name contains the keyword; false otherwise.
     *
     * @param keyword The word that the task must contain.
     * @return true if the task name contains the keyword; false otherwise.
     */
    public boolean nameContains(String keyword) {
        return this.name.contains(keyword);
    }
    /**
     * Returns the status icon and name of the task.
     *
     * @return the status icon and name of the task.
     */
    @Override
    public String toString() {
        String res = "[" + this.getStatusIcon() + "] " + this.name;
        return res;
    }

    /**
     * Returns a formatted String of the task information to be stored in database.
     *
     * @return the task information to be stored in the database.
     */
    public abstract String toSave();
}
