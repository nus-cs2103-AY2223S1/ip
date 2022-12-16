package duke.chatbot.commandmanager.commands.exceptions;

import duke.chatbot.personality.Personality;

/**
 * Exception that is thrown when there is no number after a command that requires one
 */
public class InvalidIndexException extends InvalidArgumentsException {
    public InvalidIndexException(Personality personality) {
        super(personality.formulateResponse("INVALID_INDEX_EXCEPTION"));
    }
}
