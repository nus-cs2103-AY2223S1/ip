package Duke.FileStorage;

/** 
 * This class represents exceptions that occurs when the
 * Storage tries to access the file.
 */
public class InvalidStorageException extends Exception {

    /**
     * Constructs the invalid storage exception.
     * @param errorMsg The error message to be shown to the user.
     */
    public InvalidStorageException(String errorMsg) {
        super(errorMsg);
    }
}