package skylark.task;

/** Represents a Task that is created by the user. */
public abstract class Task {
    /** Description of the task inputted by the user. */
    private final String description;

    /** Represents whether the task is marked as done. */
    private boolean isDone;

    /** Represents the tagging for the task, if any. */
    private String tag;

    /**
     * Returns a Task object.
     * Initialises the description variable and marks the Task as undone.
     *
     * @param description Description of the task inputted by the user.
     */
    public Task(String description) {
        assert description.length() > 0 : "Description should not be empty!";
        this.description = description;
        this.isDone = false;
        this.tag = "";
    }

    /**
     * Returns a Task object.
     * Initialises the description variable and marks the Task as undone.
     *
     * @param description Description of the task inputted by the user.
     * @param tag Tag of the task if any
     */
    public Task(String description, String tag) {
        assert description.length() > 0 : "Description should not be empty!";
        assert tag.length() > 0 : "Tag should not be empty!";
        this.description = description;
        this.isDone = false;
        this.tag = tag;
    }

    /**
     * Returns a string representation on whether the task is done.
     *
     * @return "X" if the task is done, " " if the task is undone.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the Task.
     *
     * @return Description inputted by the user.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Adds a new Tag to the task
     *
     * @param newTag New tag of the Task
     */
    public void setTag(String newTag) {
        this.tag = newTag;
    }

    /**
     * Returns the String representation of a particular task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s%s", this.getStatusIcon(),
                this.description,
                this.tag.isEmpty() ? "" : " TAG: " + this.tag);
    }

    /**
     * Returns a string representation of the task.
     * This string is used to save the task into the file for storage.
     *
     * @return String representation of the task that is parsable by the Storage object.
     */
    public String toStringFile() {
        return String.format("%d | %s%s",
                this.getStatusIcon().equals("X") ? 1 : 0,
                this.description,
                this.tag.isEmpty() ? "" : " | " + this.tag);
    }
}
