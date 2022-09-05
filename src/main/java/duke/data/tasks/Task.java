package duke.data.tasks;

import java.io.Serializable;

public abstract class Task implements Serializable {

    protected final String title;
    protected boolean isMarked;

    /**
     * Constructor for Task class
     *
     * @param title title of the task
     */
    Task(String title) {
        this.title = title;
        this.isMarked = false;
    }

    /**
     * Mark a task as completed
     */
    public void mark() {
        this.isMarked = true;
    }

    /**
     * Mark a task as uncompleted
     */
    public void unmark() {
        this.isMarked = false;
    }

    public boolean containsKeyword(String keyword) {
        return this.title.contains(keyword);
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", this.isMarked ? 'X' : ' ', this.title);
    }
}
