package byu.exceptions;

/**
 * An exception that indicates the instruction of a user input is invalid and not supported.
 */
public class InvalidInstructionException extends ByuException {

    @Override
    public String getMessage() {
        return "I don't know what you mean..";
    }
}

