package sky.exception;

/**
 * A unique exception for the Sky bot that indicates the user has typed in a nonsensical command.
 */
public class TextNoMeaningException extends Exception {
    private String message;

    public TextNoMeaningException(String str) {
        this.message = str;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
