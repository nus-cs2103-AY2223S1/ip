public class InvalidTaskNumberException extends IncompatibleCommandException {
    public InvalidTaskNumberException() {
        super("That task number doesn't exist!");
    }
}
