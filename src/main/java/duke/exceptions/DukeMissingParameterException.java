package duke.exceptions;

public class DukeMissingParameterException extends DukeBadFormatException {
    String missingParameter;

    public DukeMissingParameterException(String expectedFormat, String missingParameter) {
        super(expectedFormat);
        this.missingParameter = missingParameter;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + String.format("Missing parameter: %s", missingParameter);
    }
}
