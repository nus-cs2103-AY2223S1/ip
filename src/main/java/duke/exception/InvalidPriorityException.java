package duke.exception;

public class InvalidPriorityException extends DukeException {

    public InvalidPriorityException() {
        super("Invalid priority level! \n Valid priority level are: high , medium, low");
    }
}
