package carbon.error;

/**
 * Exception Class for invalid index input that is out of bounds.
 */
public class OutOfBoundsException extends CarbonException {
    /**
     * Constructs an instance of the exception.
     *
     * @param taskNumber The task number specified by the user.
     * @param length The total number of tasks currently stored.
     */
    public OutOfBoundsException(int taskNumber, int length) {
        super(String.format(
                "%d is a little awkward. We have %d tasks.",
                taskNumber,
                length)
        );
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return String.format(
                "%s\n    %s\n    Maybe you would like to try that again.",
                super.toString(),
                this.getMessage()
        );
    }
}
