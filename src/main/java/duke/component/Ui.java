package duke.component;

/**
 * Represents the interaction with the user.
 */
public class Ui {

    public static final String MSG_LINES = "_________________________\n";
    public static final String MSG_START = "Hey there! I'm Duke.\nWhat do you want to do today?";


    /**
     * Prints the message to the user.
     *
     * @param message Message to be printed.
     */
    public void printMessage(String message) {
        System.out.print(MSG_LINES + message + "\n" + MSG_LINES);
    }

    /**
     * Welcomes the user.
     */
    public void welcome() {
        this.printMessage(MSG_START);
    }

}
