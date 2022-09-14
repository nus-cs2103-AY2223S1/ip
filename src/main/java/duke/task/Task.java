package duke.task;

import java.time.LocalDate;

/**
 * Represents a task.
 */
public class Task {
    protected String description;
    private boolean isDone;

    /**
     * Constructs a Task instance with a description.
     *
     * @param str description of the task.
     */
    public Task(String str) {
        description = str;
        isDone = false;
    }

    /**
     * Returns "X" if the task is done,
     * " " if the task is not done yet.
     *
     * @return A string.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done yet.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns true if the description of the task contains the keyword.
     * Returns false if the description of the task does not contain the keyword.
     *
     * @param keyword Keyword to be checked.
     * @return A boolean value.
     */
    public boolean matchDescription(String keyword) {
        return this.description.contains(keyword);
    }

    /**
     * Checks if a task has a matching date.
     *
     * @param localDate Given date.
     * @return False.
     */
    public boolean matchDate(LocalDate localDate) {
        return false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns if a given object is equal to a task.
     *
     * @param object Given object.
     * @return True if the two are equal, false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof Task) {
            Task task = (Task) object;
            if (this == task) {
                return true;
            } else {
                return this.description.equals(task.description);
            }

        } else {
            return false;
        }
    }

}
