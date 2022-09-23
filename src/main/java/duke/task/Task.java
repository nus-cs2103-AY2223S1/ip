package duke.task;

/**
 * Represents Tasks save by Duke.
 */
public abstract class Task {
    private String name;
    private boolean isDone;

    protected Task(String name) {
        setName(name);
        setIsDone(false);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    private void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public abstract String toFormattedString();

    public abstract Task clone();

    public abstract boolean isSameTask(Task task);

    /**
     * Return the String representation of the Task.
     * @return The String representation of the Task.
     */
    @Override
    public String toString() {
        return (isDone ? "[X]" : "[ ]") + " " + this.getName();
    }

}
