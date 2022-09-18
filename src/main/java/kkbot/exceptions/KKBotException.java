package kkbot.exceptions;

/**
 * KKBotException class to encompass exceptions thrown by kkbot.kkbot.
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
