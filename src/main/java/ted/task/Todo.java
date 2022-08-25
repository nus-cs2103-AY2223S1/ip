package ted.task;

/**
 * Represents task to do. A <code>Todo</code> object corresponds to a task description
 * that needs to be done given by the user.
 */
public class Todo extends Task {
    /**
     * Creates Todo object with default not done status.
     *
     * @param taskDescription task name.
     */
    public Todo(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Creates Todo object that is specified to be done or not done.
     *
     * @param taskDescription task name.
     * @param isTaskDone boolean indicating task's done status.
     */
    public Todo(String taskDescription, boolean isTaskDone) {
        super(taskDescription, isTaskDone);
    }

    /**
     * Returns Todo in the correct String format for bot response.
     *
     * @return String that represents Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns Todo in the correct String format to write to file.
     *
     * @return String that represents Todo.
     */
    @Override
    public String toFileString() {
        return "T | " + super.toFileString() + "\n";
    }
}
