package duke.chatbot.taskmanager.exceptions;

import duke.chatbot.personality.Personality;

/**
 * Exception that is thrown when there is a problem when saving data.
 */
public class SaveDataException extends Exception {
    /**
     * Exception that handles a problem when saving data.
     */
    public SaveDataException(Personality personality) {
        super(personality.formulateResponse("SAVE_DATA_EXCEPTION"));
    }
}
