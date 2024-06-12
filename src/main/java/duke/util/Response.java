package duke.util;

/**
 * A class that wraps a string and a boolean to be passed from Duke to MainWindow.
 * Thus, MainWindow knows when to stop.
 */
public class Response {
    private final String message;
    private final boolean isExit;

    /**
     * The standard constructor.
     *
     * @param message String to be printed on the window.
     * @param isExit Boolean indicating whether the window should be closed.
     */
    public Response(String message, boolean isExit) {
        this.message = message;
        this.isExit = isExit;
    }

    /**
     * Public getter of isExit.
     *
     * @return Boolean indicating whether the program should terminate after this response.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Public getter of message.
     *
     * @return String to be printed on the window.
     */
    public String getMessage() {
        return message;
    }
}
