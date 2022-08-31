package carbon.error;

/**
 * Exception Class for invalid inputs regarding date and time.
 */
public class InvalidTimeException extends CarbonException {
    /** {@inheritDoc} */
    public InvalidTimeException(String input) {
        super(input);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return String.format(
                "%s\n    The time '%s' looks a little wonky.\n"
                + "    Please input in YYYY-MM-DD format.",
                super.toString(),
                this.getMessage()
                );
    }
}
