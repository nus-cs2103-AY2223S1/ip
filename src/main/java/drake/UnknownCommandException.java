package drake;

/**
 * An exception for when an unknown command is entered with user-facing messages that sound like Drake.
 */
public class UnknownCommandException extends DrakeException {

    /**
     * Constructor with the Drake-sounding exception for an unknown command.
     */
    public UnknownCommandException() {
        super("Uh oh spaghettios I don't know what that means!");
    }
}
