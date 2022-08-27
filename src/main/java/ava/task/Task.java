package ava.task;

import ava.Ui;
import ava.processor.Storage;
import ava.processor.TaskList;

/**
 * Abstract class to represent the tasks.
 */
public abstract class Task {
    public Boolean isBye;
    protected String description;
    protected Boolean isDone;

    /**
     * The constructor for task.
     *
     * @param description Task description.
     * @param isDone Task state.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.isBye = false;
    }

    /**
     * Returns the current status of task.
     *
     * @return String status.
     */
    public String getStatus() {
        return (this.isDone ? "[X] " : "[ ] ");
    }

    /**
     * Marks done a task.
     *
     * @return Task object.
     */
    public Task markDone() {
        this.isDone = true;
        return this;
    }

    /**
     * Marks undone a task.
     *
     * @return Task object.
     */
    public Task markUndone() {
        this.isDone = false;
        return this;
    }

    /**
     * Returns the description of the current task.
     *
     * @return String description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Changes the format of Task to write to the file.
     *
     * @return String format to write to file.
     */
    public String formatChange() {
        String mark = isDone ? "1" : "0";
        return "|" + mark + "0" + this.description;
    }

    /**
     * Overridden toString method for task details.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return this.getStatus() + this.description;
    }

    /**
     * Executes process of the given task.
     *
     * @param tasks TaskList.
     * @param ui Class to print the ui.
     * @param storage Class to write/read commands from file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {

    };
}
