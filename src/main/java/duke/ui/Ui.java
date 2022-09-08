package duke.ui;

/**
 * Handles interaction with the user.
 * Collects user input and provides responses to the user.
 *
 * @author Tan Jun Wei
 */
public class Ui {
    /* Stores text to output to user */
    private StringBuilder currOutput;

    /**
     * Constructs a new Ui object.
     */
    public Ui() {
        currOutput = new StringBuilder();
    }

    /**
     * Displays a loading error to the user.
     */
    public void showLoadingError() {
        currOutput.append("Duke is unable to load saved file" + "\n");
    }

    /**
     * Displays a string to the user.
     *
     * @param output The string to be displayed.
     */
    public void showOutput(String output) {
        currOutput.append(output + "\n");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        currOutput.append(message + "\n");
    }

    /**
     * Displays a dotted line to the user.
     */
    public void showLine() {
        currOutput.append("-----------------------" + "\n");
    }

    public String getOutput() {
        String str = currOutput.toString();
        currOutput = new StringBuilder();
        return str;
    }
}
