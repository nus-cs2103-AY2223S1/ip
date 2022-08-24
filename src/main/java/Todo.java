/**
 * Represents a todo, a type of task
 */
public class Todo extends Task {
    /**
     * Constructs a todo with the specified description
     *
     * @param description The specified description.
     * @param isDone      The boolean indicating whether the task is done.
     */
    Todo(String description, boolean isDone) {
        super(description, isDone);
        this.taskType = TaskType.T;
    }
}
