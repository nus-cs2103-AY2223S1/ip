public class InvalidTaskIndexException extends InvalidCommandException {
    public InvalidTaskIndexException() {
        super("Command should be followed by a number. For example: mark 2, unmark 3, delete 4.");
    }
}
