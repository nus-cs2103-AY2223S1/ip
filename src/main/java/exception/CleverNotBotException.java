package exception;

import clevernotbot.UI;

/**
 * Deals with exception
 */
public class CleverNotBotException extends Exception {
    private String errorMessage;
    private UI textBox;

    /**
     * Constructor for CleverNotBotException.
     *
     * @param errorMessage Error message logged in the system.
     * @param textBox      For textbox wrapping.(Removed for now)
     */
    public CleverNotBotException(String errorMessage, UI textBox) {
        this.errorMessage = errorMessage;
        this.textBox = textBox;
    }

    /**
     * Converts <Code>CleverNotBotException</Code> to a string.
     *
     * @return Exception text.
     */
    @Override
    /*public String toString(){
        return textBox.coverText(errorMessage);
    }
     */
    public String toString() {
        return errorMessage;
    }
}
