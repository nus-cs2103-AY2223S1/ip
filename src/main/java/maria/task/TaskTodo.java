package maria.task;

/**
 * Represents a Task of type Todo.
 */
public class TaskTodo extends Task{

    /**
     * Creates a Todo Task.
     * @param name The name of the task
     * @param done Whether the task is completed
     * @throws TaskNoNameException If the name is empty
     */
    public TaskTodo(String name, boolean done) throws TaskNoNameException {
        super(name, done);
    }

    /**
     * Gets the string representation of the task.
     * @return The string representation of the task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
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