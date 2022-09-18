package duke.exceptions;

/**
 * Exception for missing parameters in user input.
 */
public class DukeMissingParameterException extends DukeBadFormatException {
    private String missingParameter;

    /**
     * Constructs a new DukeMissingParameterException.
     *
     * @param expectedFormat Expected format for the user.
     * @param missingParameter Parameter missing in the command.
     */
    public DukeMissingParameterException(String expectedFormat, String missingParameter) {
        super(expectedFormat);
        this.missingParameter = missingParameter;
    }

    /**
     * Returns the exception message.
     *
     * @return Exception message.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + String.format("Missing parameter: %s", missingParameter);
    }
}
