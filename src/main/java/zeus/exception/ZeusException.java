package zeus.exception;

/**
 * Class that inherits from Exception to represent exceptions specific to Duke.
 */
public class ZeusException extends Exception {
    /**
     * Constructor for ZeusException class.
     *
     * @param message a String that represents the exception message
     */
    public ZeusException(String message) {
        super(message);
    }
}
