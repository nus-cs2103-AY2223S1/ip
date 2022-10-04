package duke;

/**
 * A class in charge of interactions with the user.
 */
public class Ui {

    /**
     * Constructor for the Ui class.
     */
    public Ui() {
    }

    /**
     * Alerts user that given file path does not contain a file.
     */
    public void showLoadingError() {
        System.err.println("File path does not contain a file!\n"
                + "But no worries, I have created one for you, add a task now!");
    }
}
