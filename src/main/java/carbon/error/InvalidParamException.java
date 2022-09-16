package carbon.error;

/**
 * Exception class for invalid input parameters for a command.
 */
public class InvalidParamException extends CarbonException {
    /** {@inheritDoc} */
    public InvalidParamException(String input) {
        super(input);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return String.format(
                "%s\nLooks like you're missing valid details for your '%s'.",
                super.toString(),
                this.getMessage()
                );
    }
}
