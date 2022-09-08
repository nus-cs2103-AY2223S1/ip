package task;

/**
 * Represents a more specific task with no time limit attached to it.
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo.
     *
     * @param name      Name of the task.
     * @param completed The status of the task. (Completed or not)
     */
    public ToDo(String name, boolean completed) {
        super(name, completed);
    }

    /**
     * Returns the deadline for Todo which is just infinite.
     *
     * @return Deadline for Todo.
     */
    @Override
    public String getTime() {
        return "inf";
    }

    /**
     * Returns The type of task.
     *
     * @return Type of task.
     */
    @Override
    public String getTaskType() {
        return "T";
    }

    /**
     * Toggles the completion of todo task.
     *
     * @return A toggled version of todo. (Completed = true -> Completed = false)
     */
    @Override
    public Task toggleCompleted() {
        return new ToDo(getName(), !isCompleted());
    }

    /**
     * Converts <Code>ToDo</Code> to a string.
     *
     * @return A string that represent a todo. E.g. [E][X] get a book /at Aug 6th 2-4pm.
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s", checkMarked(), getName());
    }
}
