package drake;

/**
 * An exception with user-facing messages that sound like Drake.
 */
public class DrakeException extends Exception {

    /**
     * Constructor
     *
     * @param errorMessage  User-facing error message that sound like Drake.
     */
    public DrakeException(String errorMessage) {
        super(errorMessage);
    }
}
