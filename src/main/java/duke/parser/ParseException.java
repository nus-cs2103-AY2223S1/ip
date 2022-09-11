package duke.parser;

import duke.DukeException;

/**
 * Exception thrown in case of a parse error.
 */
public class ParseException extends DukeException {
    public ParseException(String str) {
        super("Parsing error: " + str);
    }

    public ParseException(String str, String msg) {
        super("Parsing error: " + str + " (" + msg + ")");
    }
}
