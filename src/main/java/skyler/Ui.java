package skyler;

/**
 * Represents an interface for user interactions
 */
public class Ui {
    protected static final String GREETING = "Hello! I'm Skyler\nHow may I help you?";

    public String showEmptyDescriptionError() {
        return "Oh no! Cannot execute command without description.";
    }

    public String showTaskNotRecognisedError() {
        return "Oops! I'm sorry, I don't know what that means.";
    }
}
