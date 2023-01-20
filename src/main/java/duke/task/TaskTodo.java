package duke.task;

import duke.enums.Command;

/**
 * The {@code TaskTodo} class stores relevant information for a todoTask.
 */
public class TaskTodo extends Task {

    /**
     * Constructs a new taskTodo.
     *
     * @param taskName a string representing the name of the task.
     */
    public TaskTodo(String taskName) {
        super(taskName);
    }

    /**
     * Constructs a taskTodo specifying whether it is done.
     *
     * @param taskName a string representing the name of the task.
     * @param done     a boolean representing if the task is done.
     */
    public TaskTodo(String taskName, boolean done) {
        super(taskName, done);
    }

    /**
     * Returns a string representing the taskTodo.
     *
     * @return a string representing the taskTodo.
     */
    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }

    /**
     * Returns a string representing of a taskTodo for storage.
     *
     * @return a string representing of a taskTodo for storage.
     */
    @Override
    public String toStorageString() {
        return String.format("%s %s\n%s", Command.TODO.getValue(), getTaskName(), super.toStorageString());
    }
}
