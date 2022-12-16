package duke.chatbot.commandmanager.commands.exceptions;

import duke.chatbot.personality.Personality;

/**
 * Exception that is thrown when there is an invalid command.
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException(Personality personality) {
        super(personality.formulateResponse("INVALID_COMMAND_EXCEPTION"));
    }
}
