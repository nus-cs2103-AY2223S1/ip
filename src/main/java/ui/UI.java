package ui;

/**
 * Deals with interactions with the user
 */
public class UI {
    private String currentInput;
    private String response;

    /**
     * Prints errorMsg,
     * usually DukeException.getMessage().
     * @param errorMsg Error message to print.
     */
    public void showError(String errorMsg) {
        this.response = errorMsg;
    }

    /**
     * Prints when storage could not be synced.
     */
    public void showLoadingError() {
        this.response = "error loading";
    }

    /**
     * Prints a general message/
     * @param msg Message to be included.
     */
    public void showMessage(String msg) {
        this.response = msg;
    }

    /**
     * Prints when user terminates program
     * with bye command.
     */
    public void showExitMessage() {
        this.response = "Thank you for swinging by :)";
    }

    /**
     * Prints when user inputs
     * help command.
     */
    public void showHelpMessage() {
        String msg = "Hi, it seems you are having trouble using Falcon, "
                + "\ntry these command: "
                + "\ntodo\nlist\nevent\ndeadline\nmark\nunmark\nlongdesc\nistoday\nfind";
        this.response = msg;
    }

    /**
     * Returns line user has entered at System.in.
     * @return User input line.
     */
    public String getCurrentInput() {
        return currentInput;
    }

    public String getResponse() {
        return this.response;
    }

    public void setCurrentInput(String input) {
        this.currentInput = input;
    }
}
