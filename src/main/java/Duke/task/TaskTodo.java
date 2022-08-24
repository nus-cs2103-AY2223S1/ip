package Duke.task;

import Duke.enums.Command;

/**
 * The {@code TaskTodo} class stores relevant information for a todoTask.
 */
public class TaskTodo extends Task {

    /**
     * Constructor for a taskTodo.
     *
     * @param taskName a string representing the name of the task.
     */
    public TaskTodo(String taskName) {
        super(taskName);
    }

    /**
     * Constructor for a taskTodo.
     *
     * @param taskName a string representing the name of the task.
     * @param done     a boolean representing if the task is done.
     */
    public TaskTodo(String taskName, boolean done) {
        super(taskName, done);
    }

    /**
     * Returns string representation of a taskTodo.
     *
     * @return a string representing the taskTodo.
     */
    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }

    /**
     * Returns a string representation to store taskTodo in a file.
     *
     * @return The string representation of a taskTodo for storage.
     */
    @Override
    public String toStorageString() {
        return String.format("%s %s\n%s", Command.TODO.getValue(), getTaskName(), super.toStorageString());
    }
}
