package duke;

/**
 * Encapsulate a Task that user adds to the TaskList.
 */
public abstract class Task {

    private String description;

    private boolean isDone;

    /**
     * Constructs an instance of Task.
     *
     * @param description name of item.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets description of task
     *
     * @return  name of item.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks item as Done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks item as not Done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Updates item's date.
     */
    public abstract void updateDate(String updatedDate) throws DukeException;

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + this.description;
        }
        return "[ ] " + this.description;
    }

    /**
     * Returns string representation of data.
     *
     * @return String to be stored in data file.
     */
    public String toStorageString() {
        if (isDone) {
            return " | " + "1" + " | " + this.description;
        }
        return " | " + "0" + " | " + this.description;
    }

}
