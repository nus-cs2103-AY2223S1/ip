package exceptions;

/**
 * An exception that indicates the first instruction word of a command is invalid and not supported.
 */
public class InvalidInstructionException extends ByuException {

    @Override
    public String getMessage() {
        return "I'm sorry but I don't know what that means.";
    }
}

