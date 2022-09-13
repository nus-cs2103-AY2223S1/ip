package duke.exceptions;

/**
 * Representation of an exception when Duke cannot find specified task.
 */
public class CannotFindTaskException extends DukeException {
    public CannotFindTaskException() {
        super("BROTHER!\nI CANNOT FIND THE TASK THAT YOU WANT TO MODIFY!");
    }
}
