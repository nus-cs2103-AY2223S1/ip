package duke;

/**
 * Represents the response of executing a command.
 */
public class Response {

    private String message;
    private boolean isBye;

    /**
     * Initialises the Response with the message of the execution and whether it is bye.
     * @param message Message to display to user after executing command.
     * @param isBye Boolean to represent if the app should be closed.
     */
    public Response(String message, boolean isBye) {
        this.message = message;
        this.isBye = isBye;
    }

    /**
     * Gets the message of the response.
     * @return Message string.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Checks if application should exit.
     * @return Boolean if application should exit.
     */
    public boolean isExit() {
        return this.isBye;
    }

}
