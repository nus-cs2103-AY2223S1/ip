package duke;

import duke.DukeException;

public class DukeUnknownDateException extends DukeException {
    private static final String MSG = "The additional argument for %s is of invalid format\n";

    DukeUnknownDateException(String command) {
        super(String.format(MSG, command));
    }
}
