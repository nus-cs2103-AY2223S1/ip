package exceptions;

public class InvalidInstructionException extends DukeException {

    @Override
    public String getMessage() {
        return "I'm sorry but I don't know what that means.\n";
    }
}

