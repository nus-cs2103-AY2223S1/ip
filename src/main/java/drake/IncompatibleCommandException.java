package drake;

/**
 * An exception for incompatible commands with user-facing messages that sound like Drake.
 */
public class IncompatibleCommandException extends DrakeException {

    /**
     * Constructor.
     *
     * @param error Customised error message for the incompatible commands.
     */
    public IncompatibleCommandException(String error) {
        super("These words you just typed go together like oil mixes with water. " + error);
    }
}
