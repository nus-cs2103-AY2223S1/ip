package yilia.exception;

/**
 * Represents an exception to be thrown when keyword is not given.
 */
public class KeywordMissingException extends Exception {
    /**
     * Returns the message of the exception.
     *
     * @return The message of the exception.
     */
    @Override
    public String getMessage() {
        return "Please input keyword to search.";
    }
}
