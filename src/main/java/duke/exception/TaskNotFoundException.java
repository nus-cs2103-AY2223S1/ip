package duke.exception;

/**
 * The DukeRuntimeException when index is not found in the TaskList.
 */
public class TaskNotFoundException extends DukeRuntimeException {
    /**
     * Constructs TaskNotFoundException
     * @param idTask The index of the Task.
     */
    public TaskNotFoundException(int idTask) {
        super("Task No.: " + idTask + " is not found in the task list. \n"
                + "Try command [list] to show the tasks you may choose from.");
    }
}
