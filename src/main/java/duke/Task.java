package duke;

/**
 * Encapsulate a Task that user adds to the TaskList.
 */
public abstract class Task {

    private String description;

    private boolean isDone;

    /**
     * Class constructor for ListItem.
     *
     * @param description name of item.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Method to get the name of the item.
     *
     * @return  name of item.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Mark item as Done.
     *
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark item as not Done.
     *
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + this.description;
        }
        return "[ ] " + this.description;
    }

    public String toStorageString() {
        if (isDone) {
            return " | " + "1" + " | " + this.description;
        }
        return " | " + "0" + " | " + this.description;
    }

}
