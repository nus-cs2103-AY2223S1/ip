package duke.component;

/**
 * Represents the interaction with the user.
 */
public class Ui {

    private static final String LINES = "_________________________\n";
    private static final String START = "Hey there! I'm Duke.\nWhat do you want to do today?";


    /**
     * Prints the message to the user.
     *
     * @param message Message to be printed.
     */
    public void printMessage(String message) {
        System.out.print(LINES + message + "\n" + LINES);
    }

    /**
     * Welcomes the user.
     */
    public void welcome() {
        this.printMessage(START);
    }

}
