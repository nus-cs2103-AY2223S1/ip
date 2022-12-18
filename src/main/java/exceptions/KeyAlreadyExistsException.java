package exceptions;

/**
 * KeyAlreadyExistsException is thrown in any collection where the key being added already exists
 * in the collection
 */
public class KeyAlreadyExistsException extends IllegalArgumentException {

    /**
     * Creates a KeyAlreadyExistsException with no arguments
     */
    public KeyAlreadyExistsException() {
        super();
    }

    /**
     * Creates a KeyAlreadyExistsException
     * @param msg the message to send
     */
    public KeyAlreadyExistsException(String msg) {
        super(msg);
    }
}
