package exceptions;

public class DuplicateException extends DukeException {

    @Override
    public String getMessage() {
        return "The task already exists!";
    }
}
