package drake;

/**
 * An exception for tasks without a description with a user-facing message that sound like Drake.
 */
public class EmptyDescriptionException extends DrakeException {

    /**
     * Constructor
     */
    public EmptyDescriptionException() {
        super("Well well well! A task without a description is like a picture of Singapore without MBS");
    }
}
