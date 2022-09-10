package task;

/**
 * Represents a todo, a type of task.
 */
public class Todo extends Task {
    /**
     * Constructs a todo with description and a boolean indicating isDone.
     *
     * @param description The specified description.
     * @param isDone      The boolean indicating whether the task is done.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
        this.taskType = TaskType.T;
    }
    /**
     * Constructs a todo with description, a boolean indicating isDone, and a tag.
     *
     * @param description The specified description.
     * @param isDone      The boolean indicating whether the task is done.
     * @param tag         The specified tag.
     */
    public Todo(String description, boolean isDone, String tag) {
        this(description, isDone);
        this.tag = tag;
    }
}
