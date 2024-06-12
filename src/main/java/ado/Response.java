package ado;

/**
 * Manages the response from chatbot to handle the GUI
 */
public class Response {
    private String text;
    private boolean isExit;
    private boolean isError;

    /**
     * Creates a response.
     *
     * @param text Response from the chatbot.
     * @param isExit Is  true if response is an exit message.
     * @param isError Is true if response is an error message.
     */
    public Response(String text, boolean isExit, boolean isError) {
        this.text = text;
        this.isExit = isExit;
        this.isError = isError;
    }

    public String getText() {
        return text;
    }

    public boolean isExitResponse() {
        return isExit;
    }

    public boolean isErrorResponse() {
        return isError;
    }

}
