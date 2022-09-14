package duke.exceptions;

/**
 * Represents a type of exception that occurs when duke fails to parse the input.
 */
public class ParseInputException extends DukeException {

    /**
     * Constructs a ParseException instance with an error message as String.
     *
     * @param string Error message.
     */
    public ParseInputException(String string) {
        super(string);
    }

}
