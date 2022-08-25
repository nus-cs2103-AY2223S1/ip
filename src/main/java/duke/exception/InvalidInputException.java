package duke.exception;
public class InvalidInputException extends DukeException{

    /**
     * Constructor for InvalidInputException.
     *
     * @param input The invalid input that the user keyed in.
     * @param command The type of command being carried out.
     */
    public InvalidInputException(String input, String command) {
        super("Oops, " + input + " is not a valid argument for " + command);
    }

    /**
     * Returns the String representation of error.
     *
     * @return The String representation of error.
     */
    @Override
    public String toString() {
        return this.getMessage();
    }

}