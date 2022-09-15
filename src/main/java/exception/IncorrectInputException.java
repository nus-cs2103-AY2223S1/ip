package exception;

public class IncorrectInputException extends Exception {

    private String errorMessage;

    public IncorrectInputException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    /**
     * Returns a String object representing this DukeException's value.
     *
     * @return the string representation of the specified DukeException
     */
    @Override
    public String toString() {

        return ("LUNA cannot understand your input... " + errorMessage);
    }

}
