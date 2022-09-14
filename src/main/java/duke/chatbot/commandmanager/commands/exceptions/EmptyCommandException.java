package duke.chatbot.commandmanager.commands.exceptions;

import duke.chatbot.personality.Personality;

/**
 * Exception that is thrown when there is an empty command.
 */
public class EmptyCommandException extends Exception {
    public EmptyCommandException(Personality personality) {
        super(personality.formulateResponse("EMPTY_COMMAND_EXCEPTION"));
    }
}
