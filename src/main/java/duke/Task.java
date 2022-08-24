package duke;

public abstract class Task {
    private String task;
    private boolean isDone;

    public Task() {
        this.task = "";
        isDone = false;
    }

    public Task(String task) {
        this.task = task;
        isDone = false;
    }

    public Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return isDone
                ? "[X]"
                : "[ ]";
    }

    /**
     * Returns whether the string s is contained in
     * the task description.
     *
     * @param s the string to be checked.
     * @return whether the string is contained in
     *     the task description.
     */
    public boolean isStringContained(String s) {
        return task.contains(s);
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + task;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public String getTask() {
        return task;
    }

    public abstract String toSaveFileString();
}
