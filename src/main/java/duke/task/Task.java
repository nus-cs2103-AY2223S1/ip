package duke.task;

/**
 * Task class which represents a task, e.g. Deadlines, Events, Todos.
 *
 * @author Kavan
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class.
     *
     * @param description Description of Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of a Task as a String, where 'X' is done and ' ' is undone.
     *
     * @return Status of a Task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Mark a Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark a Task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns true if input keyword is found in Task description.
     *
     * @param keyword Keyword to search.
     * @return Boolean determining presence of keyword in Task description.
     */
    public boolean containsKeyword(String keyword) {
        String[] descriptionDelimited = this.description.split(" ");
        for (String word : descriptionDelimited) {
            if (keyword.equals(word)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return Task as a String to be saved in a .txt file.
     *
     * @return Task as a String.
     */
    abstract String storedTaskString();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}