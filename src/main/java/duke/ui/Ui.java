package duke.ui;

import java.util.Scanner;
/**
 * Represents the user interface. Interact with user.
 */
public class Ui {
    Scanner s;
    public Ui() {
        this.s = new Scanner(System.in);
    }
    /**
     * Show the welcome message.
     */
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Duke\n"+"What can I do for you?");
    }

    public void showFileCreatingError() {
        System.out.println("Sorry, something went wrong when opening the file.");
    }
    public void showGoodbyeMessage() {
        System.out.println("Goodbye, hope to see you soon.");
    }
    /**
     * Read command line by line from System.in.
     * @return A string of a line from System.in describing the user's command.
     */
    public String readCommand() {
        String res = s.nextLine();
        return res;
    }
    public String showDukeException(String s) {
        return s;
    }
}
