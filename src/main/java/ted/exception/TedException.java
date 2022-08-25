package ted.exception;

/**
 * Represents exceptions that arise from the use of the bot.
 */
public class TedException extends Exception {
    /**
     * Creates TedException object with specific error message.
     *
     * @param message error message.
     */
    public TedException(String message) {
        super(message);
    }
}
