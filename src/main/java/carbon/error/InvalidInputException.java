package carbon.error;

/**
 * Exception Class for invalid command by user.
 */
public class InvalidInputException extends CarbonException {
    /** @inheritDoc */
    public InvalidInputException(String input) {
        super(input);
    }

    /** @inheritDoc */
    @Override
    public String toString() {
        return String.format(
                "%s\n    '%s'? I have no idea what that is.",
                super.toString(),
                this.getMessage()
                );
    }
}
