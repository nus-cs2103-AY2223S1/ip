package duke.exception;

import duke.exception.DukeException;

public class DukeRuntimeException extends DukeException {
    public DukeRuntimeException(String msg) {
        super(" I'm sorry that an error occurs when executing your command, "
                + "please check with following information:\n" + msg);
    }
}
