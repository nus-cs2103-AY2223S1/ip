package duke.exception;
public class MissingInputException extends DukeException{

    /**
     * Constructor for MissingInputException.
     *
     * @param input The input that the user did not key in.
     * @param command The type of command being carried out.
     */
    public MissingInputException(String input, String command) {
        super("Oops, the " + input + " of a " + command + " cannot be empty");
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