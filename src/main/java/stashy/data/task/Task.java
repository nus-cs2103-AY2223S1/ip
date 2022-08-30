package stashy.data.task;

public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task(String description) {
        this(description, false);
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsNotDone() {
        this.isDone = false;
    }

    /**
     * Checks if the task description contains a particular text
     *
     * @param text The text of interest
     * @return A true or false boolean
     */
    public boolean containsText(String text) {
        return this.description.contains(text);
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}