package yilia.exception;

/**
 * Represents an exception to be thrown when keyword is not given.
 */
public class KeywordMissingException extends Exception {
    @Override
    public String getMessage() {
        return "Please input keyword to search.";
    }
}
