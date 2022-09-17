package Duke.FileStorage;

/**
 * This class represents exceptions that occur when the Storage
 * fails to decode the tasks from the file.
 */
public class InvalidFileContentException extends Exception {

    /**
     * Constructs the invalid file content exception.
     * @param errMsg The error message to be shown to the user.
     */
    public InvalidFileContentException(String errMsg) {
        super(errMsg);
    }
}
