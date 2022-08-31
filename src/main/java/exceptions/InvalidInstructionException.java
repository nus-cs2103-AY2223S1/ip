package exceptions;

public class InvalidInstructionException extends DukeException {

    public InvalidInstructionException() {
        super("I'm sorry but I don't know what that means.\n");
    }

}
