package duke.task;

/**
 * Parent class for all Task objects
 */
public abstract class Task {
    public static final String SAVE_SEPARATOR = "%#%";

    private String description;
    private boolean isDone = false;

    /**
     * Constructs a Task object with a certain description.
     * @param description Description of the Task object.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Constructs a Task object with a certain description and is done status.
     * @param description Description of the Task object.
     * @param isDone Is done status of the Task object.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the string representation of the Task object.
     * @return String representation of the Task object.
     */
    @Override
    public String toString() {
        return "[" + (this.isDone ? "x" : " ") + "] " + this.description;
    }

    /**
     * Changes the status of the Task object.
     * @param isDone Updated isDone status.
     */
    public void changeStatus(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Gets the isDone status of the Task.
     * @return IsDone status.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Gets the isDone status of the Task object in the form of a string, "1" being true and "0" being false.
     * @return IsDone status in a string.
     */
    public String getIsDoneString() {
        return this.isDone ? "1" : "0";
    }

    /**
     * Gets the description of the Task object.
     * @return Description of the Task object.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * String representation of the Task object to be saved to and loaded from.
     * @return String representation of the Task object to be saved.
     */
    public abstract String toSave();
}
