package duke.ui;

import java.util.Scanner;

/**
 * Displays messages and interacts with the user
 */
public class Ui {

    // LINE provides the string representation of a horizontal line
    private static final String LINE = "__________________________________________________________________________";

    private Scanner sc = new Scanner(System.in);

    /**
     * Reads the command from the user's input
     *
     * @return the string input by the user
     */
    public String readCommand() {
        return sc.nextLine().trim();
    }

    /**
     * Displays the greeting message upon startup of the app
     */
    public void showGreetingMessage() {
        drawLine();
        System.out.println("Hello! I'm Shiba \uD83D\uDC15");
        System.out.println("The task management dog you can trust!\n");
        System.out.println("What can I do for you today?");
        drawLine();
    }

    /**
     * Displays the error message when encountered an exception
     *
     * @param errMsg the error messages
     */
    public void showErrorMessage(String errMsg) {
        System.out.println("Woof! I don't understand that :v");
        System.out.println(errMsg);
    }

    /**
     * Displays the message in the appropriate horizontal line separator
     *
     * @param msg the message to be displayed
     */
    public void showMessage(String msg) {
        System.out.println(msg);
    }

    /**
     * Draws a horizontal line to separate the comments from one another
     */
    public void drawLine() {
        System.out.println(LINE);
    }
}
