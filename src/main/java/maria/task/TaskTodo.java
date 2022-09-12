package maria.task;

/**
 * Represents a Task of type Todo.
 */
public class TaskTodo extends Task {

    /**
     * Creates a Todo Task.
     * @param name The name of the task
     * @param isDone Whether the task is completed
     * @throws TaskNoNameException If the name is empty
     */
    public TaskTodo(String name, boolean isDone) throws TaskNoNameException {
        super(name, isDone);
    }

    /**
     * Gets the string representation of the task.
     * @return The string representation of the task
     */
    @Override
    public String toString() {
        return "[Todo] " + super.toString();
    }

    /**
     * Gets the storage string representation of the task.
     * @return The storage string representation of the task
     */
    @Override
    public String toStorageString() {
        return super.toStorageString() + "|||" + "todo";
    }

}
