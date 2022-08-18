/**
 * This is an exception class when user has entered an invalid task index for marking or unmarking task.
 */
public class InvalidTaskIndex extends CaCaException {
    public InvalidTaskIndex(String message) {
        super(message);
    }
}
