package dukepro.tasks;

import java.time.LocalDate;

import dukepro.StorableObjects;

/**
 * Class for Task.
 */
public class Task extends StorableObjects {
    private String name;
    private boolean isFinished = false;

    /**
     * Creates a Task.
     *
     * @param  taskname the name of the task.
     * @return A Task.
     */
    public Task(String taskname) {
        this.name = taskname;
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        isFinished = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markAsUndone() {
        isFinished = false;
    }

    /**
     * Returns String format of this class.
     *
     * @return A String.
     */
    @Override
    public String toString() {
        if (isFinished) {
            return "[/] " + this.name;
        } else {
            return "[X] " + this.name;
        }
    }

    /**
     * Returns String format of this class to be
     * saved to the data/tasklist.
     *
     * @return A String.
     */
    public String toFileForm() {
        return this.isFinished + "," + this.name;
    }

    /**
     * Compares the date of this task.
     *
     * @return A boolean.
     */
    public boolean compareDate(LocalDate localDate) {
        return false;
    }

    public boolean getMatching(String search) {
        return this.name.contains(search);
    }
}
