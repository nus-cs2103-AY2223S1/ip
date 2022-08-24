package Duke.Task;

/**
 * A task that is not time sensitive.
 */
public class ToDoTask extends Task{

    /**
     * Constructs for an todo task.
     * @param description A description of the todo
     */
    public ToDoTask(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Encodes the todo for storage.
     * Format of the todo is TASK_TYPE|IS_MARKED|DESCRIPTION.
     * @return String encoding of the todo.
     */
    public ToDoTask(String description, boolean isMarked) {
        super(description, TaskType.TODO, isMarked);
    }
}
