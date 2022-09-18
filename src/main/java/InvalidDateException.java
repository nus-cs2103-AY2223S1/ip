/**
 * An exception thrown when user-input date is erroneous.
 *
 * @author AkkFiros
 */
public class InvalidDateException extends KKBotException {
    /**
     * Constructor for an InvalidTaskException.
     */
    public InvalidDateException(String dateFormat) {
        super(String.format("Please use the proper date format: %s!", dateFormat));
    }
}
