package duke.util;

/**
 * Represents an error response.
 */
public class Error extends Response {
    /**
     * Creates an error response with a given error message.
     *
     * @param message Error message.
     */
    public Error(String message) {
        super(message);
    }

    /**
     * Returns true, because the response is an error.
     *
     * @return True.
     */
    @Override
    public boolean isError() {
        return true;
    }
}
