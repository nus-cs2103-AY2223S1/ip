package duke.tasks;

public abstract class Task {
    private String description;
    private Boolean isDone;

    /**
     * Standard constructor that defines a Task
     * @param description Description for the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Overloaded constructor to allow for creation of pre-completed tasks.
     * @param description Description for the task
     * @param isDone Marks whether task has been completed
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }


    public String getStatusIcon() {
        return isDone? "X" : " ";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getSaveString() {
        return this.isDone + ",," + this.description + ",,";
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
