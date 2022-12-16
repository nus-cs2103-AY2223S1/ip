package duke.chatbot.commandmanager.commands.exceptions;

import duke.chatbot.personality.Personality;

/**
 * Exception that is thrown when the index is out of bounds of the task list
 */
public class NoSuchIndexException extends InvalidArgumentsException {
    public NoSuchIndexException(Personality personality) {
        super(personality.formulateResponse("NO_SUCH_INDEX_EXCEPTION"));
    }
}
