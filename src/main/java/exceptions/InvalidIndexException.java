package exceptions;

public class InvalidIndexException extends DukeException {

    @Override
    public String getMessage() {
        return "The task does not exist!";
    }

}
