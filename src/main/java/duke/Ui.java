package duke;

import java.util.Scanner;

/**
 * Ui class stores the logic behind the User interface.
 */
public class Ui {
    Scanner sc = new Scanner(System.in);

    /**
     * Initialises a Ui object.
     */
    public Ui() {
    }

    public void showOutput(String output) {
        System.out.println(output);
    }

    /**
     * User experience when an error happens.
     */
    public void showLoadingError() {

    }

    /**
     * @return String that the user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints "_____" to indicate a reply from the App.
     */
    public void showLine() {
        System.out.println("_____");
    }

    /**
     * @param message Message to be shown to the user when there is an error.
     */
    public void showError(String message) {
        System.out.println(message);
    }
}
