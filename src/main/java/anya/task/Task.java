package anya.task;

public abstract class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

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

    @Override
    public String toString() {
        String res = "[" + this.getStatusIcon() + "] " + this.name;
        return res;
    }

    public abstract String toSave();
}
