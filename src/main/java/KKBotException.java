/**
 * KKBotException class to encompass exceptions thrown by KKBot.
 *
 * @author AkkFiros
 */

public abstract class KKBotException extends Exception {
    /**
     * Constructor for a KKBotException.
     * @param message Error message shown to user.
     */
    public KKBotException(String message) {
        super("Oh no!" + message);
    }
}
