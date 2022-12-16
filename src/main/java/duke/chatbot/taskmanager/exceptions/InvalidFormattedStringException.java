package duke.chatbot.taskmanager.exceptions;

import duke.chatbot.personality.Personality;

/**
 * Exception that is thrown when there is an invalid formatted string present in the save file.
 */
public class InvalidFormattedStringException extends IndexOutOfBoundsException {
    /**
     * Exception that handles invalid formatted strings present in the save file.
     */
    public InvalidFormattedStringException(Personality personality) {
        super(personality.formulateResponse("INVALID_FORMATTED_STRING_EXCEPTION"));
    }
}
