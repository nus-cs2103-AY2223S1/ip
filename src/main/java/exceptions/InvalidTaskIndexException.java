package exceptions;

public class InvalidTaskIndexException extends Exception {
    public InvalidTaskIndexException() {
        super("There are no tasks with that index...");
    }
}
