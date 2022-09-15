package duke.tasks;

/**
 * Encapsulates a Task in Duke
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initialises the Task object
     *
     * @param description The description of the task
     */
    public Task(String description) {
        assert(description != "");
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon that indicates whether the task is marked
     *
     * @return the status icon that indicates whether the task is marked
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the string representation of the Task object
     *
     * @return the string representation of the Task object
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the Task object for storing
     * in the local file
     *
     * @return the string representation of the Task object for local file storing
     */
    public String toStringForFile() {
        if (this.isDone) {
            return "M|";
        } else {
            return "N|";
        }
    }

    /**
     * Checks if the task description contains the specified keyword
     *
     * @param keyword the keyword to be matched with the task description
     * @return a boolean value, true if the description contains the keyword, false otherwise
     */
    public boolean checkIfContains(String keyword) {
        assert(keyword != "");
        return description.contains(keyword);
    }
}