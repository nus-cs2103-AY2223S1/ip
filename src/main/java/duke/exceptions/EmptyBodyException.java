package duke.exceptions;

import static duke.common.Messages.MESSAGE_EMPTY_TASK_DESCRIPTION;

public class EmptyBodyException extends Exception {

    public EmptyBodyException() {
        super(MESSAGE_EMPTY_TASK_DESCRIPTION);
    }
}
