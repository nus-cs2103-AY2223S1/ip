package commands;

/**
 * Wrapper class for the response from a Command
 */
public class CommandResponse {
    String message;

    public CommandResponse(String message) {
        this.message = message;
    }

    /**
     * @return The message from this response
     */
    public String getMessage() {
        return message;
    }
}
