package uwu.exception;

/**
 * Signals an error when there is a problem faced in loading a file.
 */
public class LoadingFileErrorException extends UwuException {
    /**
     * Constructs a LoadingFileErrorException object.
     *
     * @param message The exception message to be displayed.
     */
    public LoadingFileErrorException(String message) {
        super(message);
    }
}
