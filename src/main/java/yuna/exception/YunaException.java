package yuna.exception;

/**
 * Exceptions related to the Yuna bot.
 *
 * @author Bryan Ng Zi Hao
 */
public class YunaException extends Exception {

    /**
     * Constructor for YunaException.
     *
     * @param message The error message to be shown.
     */
    public YunaException(String message) {
        super(message);
    }

    /**
     * Override the toString() method to display the error.
     *
     * @return A String representing error message.
     */
    @Override
    public String toString() {
        return this.getMessage();
    }
}
