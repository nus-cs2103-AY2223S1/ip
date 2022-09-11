package duke.parser;

import duke.DukeException;

/**
 * Exception thrown in case of a parse error.
 */
public class ParseException extends DukeException {

    /**
     * Constructs a new ParseException.
     *
     * @param str The string that could not be parsed.
     */
    public ParseException(String str) {
        super("Parsing error: " + str);
    }

    /**
     * Constructs a new ParseException with a help message.
     * @param str The string that could not be parsed.
     * @param msg The help message.
     */
    public ParseException(String str, String msg) {
        super(String.format("Parsing error: %s (%s)", str, msg));
    }
}
