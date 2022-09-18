package drake;

/**
 * An exception for an invalid task number with user-facing messages that sound like Drake.
 */
public class InvalidTaskNumberException extends IncompatibleCommandException {

    /**
     * Constructor
     */
    public InvalidTaskNumberException() {
        super("That task number doesn't exist!");
    }
}
