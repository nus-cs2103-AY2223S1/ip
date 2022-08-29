package duke;

/**
 * A duke.Response from the application to some user input.
 */
public class Response {
    private String message;
    private boolean isExitResponse = false;

    /**
     * Creates a response with the specified String message.
     *
     * @param message The duke.Response message.
     */
    public Response(String message) {
        this.message = message;
    }

    /**
     * Converts the duke.Response to its String representation.
     *
     * @return The String representation of the duke.Response.
     */
    @Override
    public String toString() {
        return this.message;
    }

    /**
     * Sets the response to an Exit Response.
     */
    public void setExitResponse() {
        isExitResponse = true;
    }

    /**
     * Returns a boolean indicating whether to exit the Duke window.
     * @return Boolean indicating whether it is an exitResponse.
     */
    public boolean isExitResponse() {
        return isExitResponse;
    }
}
