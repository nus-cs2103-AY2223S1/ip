package exceptions;

public class UnknownCommandException extends Exception {
    public UnknownCommandException() {
        super("I don't get what you are saying...");
    }
}
