package duke.chatbot.commandmanager.commands.exceptions;

import duke.chatbot.personality.Personality;

/**
 * Exception that is thrown when there is an invalid event time.
 */
public class InvalidEventException extends InvalidArgumentsException {
    public InvalidEventException(Personality personality, String dateFormat) {
        super(personality.formulateResponse("INVALID_EVENT_EXCEPTION", dateFormat));
    }
}
