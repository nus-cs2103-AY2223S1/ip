package spongebob.task;

/**
 * Represents an abstract class for all tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected boolean isDeleted;

    /**
     * Returns an instance of Task.
     * @param description Description of task.
     * @param isDone Is task complete.
     */
    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.isDeleted = false;
    }

    /**
     * Marks task as complete.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks task as incomplete.
     */
    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Marks task to be deleted.
     */
    public void markToDelete() {
        this.isDeleted = true;
    }

    /**
     * Return if the task is marked to be deleted.
     * @return True if the task is marked to be deleted, else false.
     */
    public boolean isToBeDeleted() {
        return this.isDeleted;
    }

    /**
     * Checks in keyword is contained in description of task.
     * @param keyword Keyboard to be searched.
     * @return True if keyword is contained in description. Else false.
     */
    public boolean matchKeyword(CharSequence keyword) {
        return this.description.contains(keyword);
    }

    /**
     * Returns string format of task for saving purpose.
     * @return String format of task.
     */
    public abstract String toStringSaveFormat();

    /**
     * Return string representation of task.
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s\n", this.isDone ? "X" : " ", this.description);
    }
}
