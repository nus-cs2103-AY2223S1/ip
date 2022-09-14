package duke.chatbot.commandmanager.commands.exceptions;

import duke.chatbot.personality.Personality;

/**
 * Exception that is thrown when there are invalid arguments to a command
 */
public class InvalidArgumentsException extends Exception {
    public InvalidArgumentsException(Personality personality) {
        super(personality.formulateResponse("INVALID_ARGUMENTS_EXCEPTION"));
    }
    public InvalidArgumentsException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
