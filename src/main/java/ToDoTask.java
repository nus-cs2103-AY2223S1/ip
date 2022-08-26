public class ToDoTask extends Task{

    /**
     * Constructs for an todo task.
     * @param description A description of the todo
     */
    public ToDoTask(String description) {
        super(description);
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
