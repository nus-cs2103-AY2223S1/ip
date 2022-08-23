package duke.util;

import duke.DukeException;

public class ParseException extends DukeException {
    public ParseException(String str) {
        super("Parsing error: " + str);
    }
}
