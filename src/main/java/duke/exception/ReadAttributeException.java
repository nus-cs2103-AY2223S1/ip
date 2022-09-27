package duke.exception;

/**
 * The DukeRuntimeException when errors in reading attributes from a formatted String.
 */
public class ReadAttributeException extends DukeRuntimeException {
    /**
     * Constructs ReadAttributeException.
     * @param className The class that reads the formatted String.
     * @param formattedString The formatted String.
     * @param message The Error Details.
     */
    public ReadAttributeException(String className, String formattedString, String message) {
        super("When reading from '" + formattedString + "' in class " + className + ":\n" + message);
    }
}
