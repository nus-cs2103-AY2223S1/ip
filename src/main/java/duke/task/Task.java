package duke.task;

/**
 * Parent of all specific Tasks
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates a Task that is not done.
     *
     * @param description Description of this Task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Creates a Task with a specified done status.
     *
     * @param isDone The done status of the Task.
     * @param description Description of this Task.
     */
    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Checks if the description of this Task contains a keyword.
     * @param keyword Keyword to check for within the description.
     * @return true if the keyword is contained in the description, false otherwise.
     */
    public boolean descriptionContains(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Marks a Task as done
     */
    public void markAsDone() {
        isDone = true;
        assert isDone == true : "Task should be marked as done.";
    }

    /**
     * Marks a Task as not done
     */
    public void markAsUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        String checkBox = isDone ? "X" : " ";
        return "[" + checkBox + "] " + description;
    }

    /**
     * Returns a String representation of the Task that is suitable for storing in a text file.
     * 
     * @return A string representation of the Task for storing in a text file.
     */
    public String toDbString() {
        String binaryIsDone = isDone ? "1" : "0";
        return binaryIsDone + " | " + description;
    }
}
