package duke.exceptions;

/**
 * Exception for bad format in user input for dates.
 */
public class DukeDateFormatException extends DukeException {
    private String invalidDate;

    /**
     * Constructs a new DukeDateFormatException.
     *
     * @param invalidDate Invalid date the user inputted.
     */
    public DukeDateFormatException(String invalidDate) {
        super("Required format: yyyy-mm-dd");
        this.invalidDate = invalidDate;
    }

    /**
     * Returns the exception message.
     *
     * @return Exception message.
     */
    @Override
    public String getMessage() {
        return super.toString() + String.format("Date found: %s", invalidDate);
    }

}
