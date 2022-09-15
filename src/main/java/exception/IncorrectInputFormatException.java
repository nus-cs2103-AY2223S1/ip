package exception;

public class IncorrectInputFormatException extends Exception {

    private String errorMessage;

    /**
     * Constructor for IncorrectInputFormatException Object
     * @param errorMessage the error message to be displayed
     */
    public IncorrectInputFormatException(String errorMessage) {
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

        return ("LUNA is questioning the format of your input... " + errorMessage);
    }

}