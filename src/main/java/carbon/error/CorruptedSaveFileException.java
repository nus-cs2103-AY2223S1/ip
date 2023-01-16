package carbon.error;

/**
 * Exception class for corrupted stored data on tasks, that cannot be read.
 */
public class CorruptedSaveFileException extends CarbonException {
    private String data;

    /**
     * Constructs an instance of the exception.
     *
     * @param data String message for the error.
     * @return CorruptedSavefileException object.
     */
    public CorruptedSaveFileException(String data) {
        super(data);
        this.data = data;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return String.format("The save file appear to be corrupted.\n"
                + "\"%s\" is not formatted properly.", data);
    }
}
