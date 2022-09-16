package duke.tasks;

import duke.exception.DukeException;
import duke.tag.Tag;

/**
 * Task is parent class of all Tasks
 */
public abstract class Task {

    /**
     * Description of task
     */
    private final String description;
    /**
     * isDone keeps track of whether the task is marked
     */
    private boolean isDone;

    private Tag tag = null;

    /**
     * Constructor of task, and initialise it as unmarked
     *
     * @param txt Description of task
     */
    public Task(String txt) {
        this.isDone = false;
        this.description = txt;
    }

    /**
     * Overloaded constructor that intialiases isDone state of task
     *
     * @param txt    Task description
     * @param isDone Whether task has been completed
     */
    public Task(String txt, boolean isDone) {
        this.isDone = isDone;
        this.description = txt;
    }

    /**
     * Gets description of task
     *
     * @return Description of task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Based on isDone, return the correct status Icon
     *
     * @return String representation of status Icon
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Mark task as done
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Mark task as undone
     */
    public void setUndone() {
        this.isDone = false;
    }

    /**
     * String representation of task
     *
     * @return String representation of task
     */
    @Override
    public String toString() {
        if (this.tag == null) {
            return this.getStatusIcon() + " " + this.description;
        } else {
            return this.getStatusIcon() + "[" + this.tag.toString() + "]" + " " + this.description;
        }
    }

    /**
     * String representation of task in storage
     *
     * @return String representation of task in storage
     */
    public String toSaveString() {
        String mark = isDone ? "1" : "0";
        String str = "| " + mark + " | " + this.description + " | ";
        if (this.tag != null) {
            return str + this.tag;
        } else {
            return str;
        }
    }

    /**
     * Adds tag to task
     *
     * @param tag Tag to be added
     * @throws DukeException If there is already tag attached to task
     */
    public void addTag(Tag tag) throws DukeException {
        if (this.tag != null) {
            throw new DukeException("There is already a tag! Consider untagging and adding a new one");
        }
        this.tag = tag;

    }

    /**
     * Removes tag from task
     *
     * @throws DukeException If there is already no tag attached to task
     */
    public void unTag() throws DukeException {
        if (this.tag == null) {
            throw new DukeException("There is no tag!");
        }
        this.tag = null;
    }
}
