package exceptions;

public class EmptyBodyException extends Exception {

    public EmptyBodyException() {
        super("Task description cannot be nothing! Since you gave me nothing I'll return you nothing!");
    }
}
