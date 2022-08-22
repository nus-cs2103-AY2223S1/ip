package Duke.Task;

import Duke.Processor.Storage;
import Duke.Processor.TaskList;
import Duke.UI;

/**
 * Class to represent the tasks.
 */
public abstract class Task {
    protected String description;
    protected Boolean isDone;
    public Boolean isBye;

    /**
     * The constructor for task.
     * @param description
     * @param isDone
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.isBye = false;
    }

    /**
     * The method to get the current status.
     * @return String
     */
    public String getStatus() {
        return (this.isDone ? "[X] " : "[ ] ");
    }

    /**
     * The method to change the current status.
     * @return Duke.Task.Task object
     */
    public Task markDone() {
        this.isDone = true;
        return this;
    }

    /**
     * The method to change the current status.
     * @return Duke.Task.Task object
     */
    public Task markUndone() {
        this.isDone = false;
        return this;
    }

    /**
     * Getter method of description.
     * @return String
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * The method to change the tasklist format to write in tasks.txt
     * @return String
     */
    public String formatChange() {
        String mark = isDone ? "1" : "0";
        return "|" + mark + "0" + this.description;
    }

    /**
     * Overridden toString method for task details
     * @return String
     */
    @Override
    public String toString() {
        return this.getStatus()  + this.description;
    }

    /**
     * Executes input task
     * @param task
     * @param ui
     */
    public void execute(TaskList task, UI ui, Storage storage) {

    };
}
