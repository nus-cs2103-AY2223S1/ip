package ren.task;

/**
 * Parent Class for all Tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a Task.
     *
     * @param description Description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the Task information for writing to a File.
     *
     * @return String with Task information.
     */
    public abstract String writeData();

    /**
     * Compares this Task to another Task by their type of task.
     *
     * @param other The task to compare with.
     * @return -1 if this task should be sorted first, 1 if the other task should be sorted first, 0 otherwise.
     */
    public abstract int compareType(Task other);

    /**
     * Compares this Task to another Task by their date.
     *
     * @param other The task to compare with.
     * @return -1 if this task should be sorted first, 1 if the other task should be sorted first, 0 otherwise.
     */
    public abstract int compareDate(Task other);

    /**
     * Compares this Task to another Task by their description.
     *
     * @param other The task to compare with.
     * @return -1 if this task should be sorted first, 1 if the other task should be sorted first, 0 otherwise.
     */
    public int compareDescription(Task other) {
        return this.description.compareToIgnoreCase(other.description);
    }

    /**
     * Compares this Task to another Task by their status.
     *
     * @param other The task to compare with.
     * @return -1 if this task should be sorted first, 1 if the other task should be sorted first, 0 otherwise.
     */
    public int compareStatus(Task other) {
        if (!this.isDone && other.isDone) {
            return -1;
        } else if (this.isDone && !other.isDone) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Sets the completion status of this task.
     *
     * @param isDone New completion status of the task.
     * @return String with message for user.
     */
    public String setDone(boolean isDone) {
        this.isDone = isDone;
        return isDone
            ? " Great job! I will mark the task as completed.\n" + "   " + this
            : " Understood. I will mark the task as uncompleted.\n" + "   " + this;
    }

    /**
     * Checks if the description of this task contains a search term.
     *
     * @param term The Search Term.
     * @return true if it contains the search term, false otherwise.
     */
    public boolean isMatch(String term) {
        return this.description.contains(term);
    }
}
