package maria.task;

/**
 * Represents the Exception where the task provided has no name.
 */
public class TaskNoNameException extends Exception {

    public TaskNoNameException(String message) {
        super(message);
    }

}
