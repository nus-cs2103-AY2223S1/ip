package dukepro.tasks;

import java.time.LocalDate;

/**
 * Class for Task.
 */
public class Task {
    private String name;
    private boolean finished = false;

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
     *
     * @return void.
     */
    public void markAsDone() {
        finished = true;
    }

    /**
     * Returns String format of this class.
     *
     * @return A String.
     */
    @Override
    public String toString() {
        if (finished) {
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
    public String fileForm() {
        return this.finished + "," + this.name;
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
