package sally.exception;

/**
 * SallyException class throws checked exception when exceptions are thrown and caught in
 * the execution of Sally.
 *
 * @author liviamil
 */

public class SallyException extends Exception {
    /**
     * Constructor for SallyException with a given message.
     *
     * @param message for a specific exception.
     */
    public SallyException(String message) {
        super(message);
    }

    /**
     * Constructor for SallyException.
     */
    public SallyException() {};

}
