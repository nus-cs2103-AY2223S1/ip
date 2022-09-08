package roger.ui;

/**
 * Encapsulates a response from Roger to the user.
 */
public class Response {
    private String message;
    private boolean isExit;
    private boolean isException;

    /**
     * Create a response.
     *
     * @param message The message from Roger to the user.
     * @param isExit True if Roger is asking to exit the program.
     * @param isException True if this response is Roger notifying the user of an exception.
     */
    public Response(String message, boolean isExit, boolean isException) {
        this.message = message;
        this.isExit = isExit;
        this.isException = isException;
    }

    public boolean isExit() {
        return isExit;
    }

    public boolean isException() {
        return isException;
    }

    public String getMessage() {
        return message;
    }
}
