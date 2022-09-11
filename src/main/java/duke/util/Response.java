package duke.util;

/**
 * Represents the response from Duke after receiving the user's input.
 */
public abstract class Response {
    /** Response message. */
    private String message;

    /**
     * Creates a Response with a given response message.
     *
     * @param message Response message.
     */
    public Response(String message) {
        this.message = message;
    }

    /**
     * Checks if a Response is a Success or an Error response.
     *
     * @return True, if a Response is a Success, false otherwise.
     */
    public abstract boolean isError();

    /**
     * Returns the message of the Response.
     *
     * @return Message of the Response.
     */
    public String getMessage() {
        return this.message;
    }
}
