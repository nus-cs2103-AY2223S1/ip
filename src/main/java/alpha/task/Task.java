package alpha.task;

import alpha.Ui;

/**
 * Stores and provides details of each task.
 */
public abstract class Task {

    /** Object of the Ui class to help display messages */
    protected Ui uI = new Ui();

    /** Description of the task */
    protected String description;

    /** Status of the task */
    protected boolean status;

    /** Type of the task */
    protected String taskType;

    /**
     * Constructor to initialise all the global variables.
     *
     * @param description To initialise the description of the task.
     * @param taskType To initialise the type of the task.
     */
    public Task(String description, String taskType) {
        this.description = description;
        this.status = false;
        this.taskType = taskType;
    }

    /**
     * Returns the description of the task.
     * It is a getter method.
     *
     * @return Task Description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status of the task.
     * It is a getter method.
     *
     * @return Task status.
     */
    public String getStatus() {
        return (status ? "X" : " ");
    }

    /**
     * Returns type of the task.
     * It is a getter method.
     *
     * @return Task type.
     */
    public String getTaskType() {
        return this.taskType;
    }

    /**
     * Changes the status of the task from unmarked to marked or vice-versa.
     *
     * @param status To change the status of the task to the given status.
     */
    public void changeStatus(boolean status) {
        this.status = status;
    }

    /**
     * Returns the details of the task as a String.
     *
     * @return String to display the details of the task.
     */
    @Override
    public String toString() {
        return String.format("[ %s ] [ %s ] %s", this.getTaskType(),
                this.getStatus(), this.getDescription());
    }
}
