package exceptions;

public class DukeTooManyArgumentException extends DukeException{

    public DukeTooManyArgumentException() {
        super("There are too many arguments for this command");
    }
}
