package duke.util;

/**
 * Represents a successful response.
 */
public class Success extends Response {
    /**
     * Creates a Success response with a given success message.
     *
     * @param message Success message.
     */
    public Success(String message) {
        super(message);
    }

    /**
     * Returns false, because the response is successful.
     *
     * @return False.
     */
    @Override
    public boolean isError() {
        return false;
    }
}
