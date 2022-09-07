package exceptions;

public class IncorrectFileInputException extends DukeException {

    @Override
    public String getMessage() {
        return "Incorrect input from file!";
    }
}
