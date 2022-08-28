package duke;

/**
 * Represents the message with appropriate flags needed by the display.
 */
public class Message {
    private boolean isExit;
    private boolean isError;
    private String text;

    /**
     * Creates the message.
     * @param text Response to user.
     * @param isExit Whether the previous command was the exit command.
     * @param isError Whether there was an error with the previous command.
     */
    public Message(String text, boolean isExit, boolean isError) {
        this.text = text;
        this.isExit = isExit;
        this.isError = isError;
    }

    public String getText() {
        return text;
    }

    public boolean getIsExit() {
        return isExit;
    }

    public boolean getIsError() {
        return isError;
    }

}
