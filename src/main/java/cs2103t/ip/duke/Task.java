package cs2103t.ip.duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the string representation of whether a task is completed.
     * @return A cross if task is complete, empty brackets if task is incomplete.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Sets the boolean isDone to true to indicate that the task has been completed.
     */
    public void makeDone() {
        this.isDone = true;
    }

    /**
     * Sets the boolean isDone to true to indicate that the task has been completed.
     */
    public void makeUndone() {
        this.isDone = false;
    }

    /**
     * Returns a message to indicate that a task has been successfully added
     * that is shown to user when a task of type deadline is added to the list.
     * @return A message shown to user upon addition of task.
     */
    public String addString(int i) {
        return "";
    }

    /**
     * Returns a string representation of the task to be saved into the file.
     * @return String representation of the task.
     */
    public String saveString() {
        return "";
    }

    /**
     * Returns a string representation of the task.
     * @return String representation of the task.
     */
    public String toString() {
        return String.format("%s %s", this.getStatusIcon(), this.description);
    }
}

