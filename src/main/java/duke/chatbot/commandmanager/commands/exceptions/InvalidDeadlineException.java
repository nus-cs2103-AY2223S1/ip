package duke.chatbot.commandmanager.commands.exceptions;

import duke.chatbot.personality.Personality;

/**
 * Exception that is thrown when there is an invalid deadline.
 */
public class InvalidDeadlineException extends InvalidArgumentsException {
    public InvalidDeadlineException(Personality personality, String dateFormat) {
        super(personality.formulateResponse("INVALID_DEADLINE_EXCEPTION", dateFormat));
    }
}
