package duke.util;

import duke.DukeException;

public class ParseException extends DukeException {
    public ParseException(String str) {
        super("Parsing error: " + str);
    }

    public ParseException(String str, String msg) {
        super("Parsing error: " + str + " (" + msg + ")");
    }
}
