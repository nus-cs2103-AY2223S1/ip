package dukeExceptions;

// Thrown when an unknown command is given, e.g. "blah"
public class UnknownCommandException extends Exception {
    public UnknownCommandException() {
        super(String.format("OOPS!!! Sorry, I do not know what that means!"));
    }
}
