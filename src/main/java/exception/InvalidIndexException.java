package exception;

public class InvalidIndexException extends Exception {
    public InvalidIndexException() {
        super("Oops, it looks like your indexing is wrong.");
    }
}