package duke.exception;

/**
 * The DukeRuntimeException when errors in reading attributes from a formatted String.
 */
public class ReadAttributeException extends RuntimeException {
    /**
     * The constructor of the Exception.
     * @param className The class that reads the formatted String.
     * @param formattedString The formatted String.
     * @param message The Error Details.
     */
    public ReadAttributeException(String className, String formattedString, String message) {
        super("When reading from '" + formattedString + "' in class " + className + ":\n" + message);
    }

    /**
     * Return boolean indicating whether this object
     * is equivalent to another object.
     *
     * @param obj The object to be checked.
     * @return The boolean whether the given object is equivalent to this object.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ReadAttributeException) {
            ReadAttributeException e = (ReadAttributeException) obj;
            if (e == null) {
                return false;
            }
            return this.getMessage().equals(e.getMessage());
        }
        return false;
    }
}
