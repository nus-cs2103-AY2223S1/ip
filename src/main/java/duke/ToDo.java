package duke;

/**
 * Represents a to-do task, or a task to be done by the user. It is a type of task, and hence
 * inherits from <code>Task</code> class.
 */
public class ToDo extends Task {
    /**
     * Constructor for To-Do task.
     *
     * @param taskName the description of the task to be done.
     * @param isDone   the completion status of the task.
     */
    public ToDo(String taskName, boolean isDone, int priority) {
        super(taskName, isDone, priority);
    }

    /**
     * Returns the string representation of the Task.
     *
     * @return string representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "]" + "[" + this.getPriority() + "] "
                + this.taskName;
    }

    /**
     * Returns the string representation of the task which is saved locally, for easy retrieval.
     *
     * @return string representation of to-do task which is saved.
     */
    @Override
    public String toStore() {
        return "|T|" + "|" + this.getStatusIcon() + "|" + "|" +
                this.priority + "| " + this.taskName;
    }
}

