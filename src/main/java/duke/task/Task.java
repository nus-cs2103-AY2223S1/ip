package duke.task;

import java.time.LocalDate;

/**
 * An abstract class representing the Tasks included in Duke.
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Constructs a Task.
     *
     * @param description the description of the task
     * @param isDone boolean value where true indicates the task is done
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns a String indicating whether the task is done.
     *
     * @return "X" if task is done, " " if task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks this task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the string format of this task including its index.
     *
     * @param index the index of this task in a list/array
     * @return a string representation of this task based on its index in a list/array.
     */
    public String toStringWithIndex(int index) {
        return index + "." + this.toString();
    }

    /**
     * Returns the string format of this task to be saved in the save file.
     *
     * @return a string representation of this task in the format it is saved in the save file.
     */
    public abstract String toFileFormat();

    /**
     * Returns a boolean indicating if this task is the same as the obj.
     *
     * @param obj the Object to be compared to
     * @return true if this task and obj are the same, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            Task tmp = (Task) obj;
            return tmp.description.equals(this.description) &&
                    tmp.isDone == this.isDone;
        }
        return false;
    }

    /**
     * String representation of this task.
     *
     * @return a string representing this task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}