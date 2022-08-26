package duke.exception;

/**
 * The DukeRuntimeException when index is not found in the TaskList.
 */
public class TaskNotFoundException extends RuntimeException {
    /**
     * The constructor of the Exception.
     * @param idTask The index of the Task.
     */
    public TaskNotFoundException(int idTask) {
        super("Task No.: " + idTask + " is not found in the task list. \n"
                + "Try command [list] to show the tasks you may choose from.");
    }

    /**
     * Return boolean indicating whether this object
     * is equivalent to another object.
     *
     * @param obj The object to be checked.
     * @return The boolean whether the given object is equivalent to this object.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TaskNotFoundException) {
            TaskNotFoundException e = (TaskNotFoundException) obj;
            if (e == null) {
                return false;
            }
            return this.getMessage().equals(e.getMessage());
        }
        return false;
    }
}
