package candice.exception;

import candice.command.CommandType;

public class EmptyCommandDescriptionException extends Exception {
    public EmptyCommandDescriptionException(CommandType taskType) {
        super("You can't just say " + taskType + " without any description.");
    }
}
