package duke.task;

/**
 * Abstract class handling logic regarding tasks.
 */
public abstract class Task {
    /* Name of task */
    protected String name;
    /* Flag representing if a task is completed */
    protected boolean isDone;
    /* Task tag, each task has 1 or no tag */
    protected Tag tag;

    /**
     * Returns string representation of Task object.
     *
     * @return String representation of Task object.
     */
    @Override
    public String toString() {
        return tag == null
                ? String.format("[%s] %s", this.getStatusIcon(), name)
                : String.format("[%s] %s [Tag: %s]", this.getStatusIcon(), name, tag);
    }

    /**
     * Returns "X" if task is completed, " " otherwise.
     *
     * @return String representation of completion status of Task object.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks Task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks Task as uncompleted.
     */
    public void markAsNotdone() {
        this.isDone = false;
    }

    /**
     * Add tag with given tag name to task.
     *
     * @param tag Name of tag to be added.
     */
    public void tag(String tag) {
        this.tag = new Tag(tag);
    }

    /**
     * Returns formatted string representation of task for save processing.
     *
     * @return Formatted string representation of task.
     */
    public abstract String convertToSaveFormat();
}
