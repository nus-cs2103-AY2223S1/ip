package duke.task;

import duke.task.Task;

/**
 * Class handling the todo task type.
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo Class.
     *
     * @param name String representation of task name.
     */
    public ToDo(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Returns string representation of ToDo object.
     *
     * @return String representation of ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns formatted string representation of task for save processing.
     *
     * @return Formatted string representation of task.
     */
    @Override
    public String convertToSaveFormat() {
        int status = isDone ? 1 : 0;
        return String.format("%s | %d | %s", "T", status, name);
    }

}
