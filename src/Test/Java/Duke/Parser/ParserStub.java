package Duke.Parser;

import Duke.Exception.DukeException;

public class ParserStub {
    public String[] tokenize(String str, String separator) throws DukeException {
        str = str.strip();
        if (str.equals("")) {
            throw DukeException.taskDescriptionException("No input");
        }
        String[] tokens = str.split(separator);
        if (tokens.length < 1 || tokens.length > 2) {
            throw DukeException.taskTokenException(tokens.length);
        }
        return tokens;
    }
}