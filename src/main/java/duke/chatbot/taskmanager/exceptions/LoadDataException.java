package duke.chatbot.taskmanager.exceptions;

import duke.chatbot.personality.Personality;

/**
 * Exception that is thrown when there is a problem when loading data.
 */
public class LoadDataException extends Exception {
    /**
     * Exception that handles a problem when loading data.
     */
    public LoadDataException(Personality personality, String exceptionMessage) {
        super(personality.formulateResponse("LOAD_DATA_EXCEPTION", exceptionMessage));
    }
}
