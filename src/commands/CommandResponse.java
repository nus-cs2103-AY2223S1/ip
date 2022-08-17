package commands;

/**
 * Wrapper class for the response from a Command
 */
public class CommandResponse {
    protected String message;
    protected boolean isExit;

    public CommandResponse(String message, boolean isExit) {
        this.message = message;
        this.isExit = isExit;
    }
    public CommandResponse(String message) {
        this(message, false);
    }

    /**
     * @return The message from this response
     */
    public String getMessage() {
        return message;
    }
    public boolean isExit() {
        return isExit;
    }
}
