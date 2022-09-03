package duke.exception;

import duke.ui.Message;

public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    public DukeException(Message message) {
        super(message.toString());
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}