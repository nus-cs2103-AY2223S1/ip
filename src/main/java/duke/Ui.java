package duke;

/**
 * Ui class deals with interactions with the user.
 */
public class Ui {
    /**
     * Constructor for Ui.
     */
    public Ui() {
    }

    /**
     * Prints a Message in a custom format.
     *
     * @param message a string with UI customisation
     */
    public void printMessage(String message) {
        String line = "    ____________________________________________________________";
        String wrappedMessage = line + "\n     " + message + "\n" + line;
        System.out.println(wrappedMessage);
    }

    /**
     * Prints a greeting message.
     */
    public void printGreetings() {
        printMessage("Hello! I'm Duke.\n     What can I do for you?");
    }

    /**
     * Prints a goodbye message.
     */
    public void printGoodbye() {
        printMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a message that informs the user of a loading error.
     */
    public void showLoadingError() {
        printMessage("There is a loading error.");
    }
}
