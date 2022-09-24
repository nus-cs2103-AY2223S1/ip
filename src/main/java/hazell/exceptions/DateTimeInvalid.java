package hazell.exceptions;

/**
 * Exception when parsing of date and time is invalid.
 */
public class DateTimeInvalid extends HazellException {
    @Override
    public String toString() {
        return String.format("Please use this format: yyyy-mm-dd", super.toString());
    }
}
