package duke.chatbot.commandmanager.commands.exceptions;

import duke.chatbot.personality.Personality;

/**
 * Exception that is thrown when there is an empty date format provided.
 */
public class EmptyDateFormatException extends InvalidArgumentsException {
    public EmptyDateFormatException(Personality personality) {
        super(personality.formulateResponse("EMPTY_DATE_FORMAT_EXCEPTION"));
    }
}
