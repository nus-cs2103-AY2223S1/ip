package duke.ui;

/**
 * The interface responsible for handling the app's interaction with the user.
 */
public class Ui {
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke\n" + "What can I do for you?";
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";

    /**
     * Welcomes the user.
     */
    public void showWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    /**
     * Bids the user goodbye. This should only be shown on the ExitCommand.
     */
    public void showGoodByeMessage() {
        System.out.println(GOODBYE_MESSAGE);
    }

    /**
     * Displays a message to the user.
     * @param message the message to be displayed
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}
