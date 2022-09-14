package duke.chatbot.personality.exceptions;

/**
 * Exception that is thrown when there is an issue loading the personality yaml file.
 */
public class LoadPersonalityException extends Exception {
    public LoadPersonalityException() {
        super("Personality failed to load!");
    }
}
