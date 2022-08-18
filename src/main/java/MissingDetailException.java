/**
 * This is an exception class when user input has missing details for deadline and event.
 */
public class MissingDetailException extends CaCaException {
    public MissingDetailException(String message) {
        super(message);
    }
}