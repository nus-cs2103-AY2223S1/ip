package duke.exception;

/**
 * TaskNotFoundDukeException is thrown if the user inputs a task number that is not in the TaskList
 */
public class TaskNotFoundDukeException extends DukeException {
    public TaskNotFoundDukeException() {
        super("I couldn't find the task you were looking for...");
    }

}
