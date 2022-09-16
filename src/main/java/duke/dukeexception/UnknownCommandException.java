package duke.dukeexception;

import duke.dukeexception.DukeException;

public class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("That command was not valid. Try again!");
    }
}
