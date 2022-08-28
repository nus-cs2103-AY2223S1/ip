package wanya;

/**
 * Represents the exceptions that arise from Wanya bot.
 */
public class WanyaException extends Exception{
    /**
     * Initialises the exception with error message.
     *
     * @param message error message to be shown.
     */
    public WanyaException(String message) {
        super("Whoopsie!!! " + message);
    }
}
