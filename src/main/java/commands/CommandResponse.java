package commands;

/**
 * Wrapper class for the response from a Command
 */
public class CommandResponse {
    protected String message;
    protected boolean isExit;

    /**
     * @param message Message to display as response
     * @param isExit true if this command should cause the conversation to terminate, else false
     */
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
}
