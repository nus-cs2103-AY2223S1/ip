package duke.task;

import duke.ui.Ui;

/**
 * Class for a singular Task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     * @param description - description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getSavedString() {
        return String.format("T | %s | %s\n", this.isDone, this.description);
    }

    @Override
    public String toString() {
        return String.format("[T] %s %s", Ui.checkbox(this.getIsDone()), this.getDescription());
    }
}
