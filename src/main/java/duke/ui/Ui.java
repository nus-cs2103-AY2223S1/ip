package duke.ui;

/**
 * Deals with interactions with the user.
 *
 * @author Rama Aryasuta Pangestu
 */
public class Ui {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private StringBuilder currentOutput;

    /**
     * Constructs a helper tool to deal with interactions with the user.
     */
    public Ui() {
        currentOutput = new StringBuilder();
    }

    /**
     * Adds the error thrown by Duke.
     *
     * @param error the error message
     */
    public void addError(String error) {
        currentOutput.append(error + "\n");
    }

    /**
     * Adds a reply by Duke to the user's input.
     *
     * @param output the reply message
     */
    public void addOutput(String output) {
        currentOutput.append(output);
    }

    /**
     * Returns the response by Duke.
     *
     * @return the response by Duke
     */
    public String getOutput() {
        String ret = currentOutput.toString();
        currentOutput = new StringBuilder();
        return ret;
    }
}
