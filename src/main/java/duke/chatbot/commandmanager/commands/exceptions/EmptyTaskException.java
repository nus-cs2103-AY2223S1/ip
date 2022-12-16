package duke.chatbot.commandmanager.commands.exceptions;

import duke.chatbot.personality.Personality;

/**
 * Exception that is thrown when there is an empty task.
 */
public class EmptyTaskException extends InvalidArgumentsException {
    public EmptyTaskException(Personality personality) {
        super(personality.formulateResponse("EMPTY_TASK_EXCEPTION"));
    }
}
