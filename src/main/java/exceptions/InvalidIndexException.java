package exceptions;

public class InvalidIndexException extends DukeException {

    public InvalidIndexException() {
        super("The task does not exist!\n");
    }

}
