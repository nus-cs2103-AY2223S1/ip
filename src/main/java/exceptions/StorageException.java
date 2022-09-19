package exceptions;

/**
 * Exception to represent Storage errors that the program should exit upon
 */
public class StorageException extends Exception {
    public StorageException(String msg) {
        super(msg);
    }
}
