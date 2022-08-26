package duke;

import java.util.Scanner;

public class Ui {

    /**
     * Display welcome message to user.
     */
    public void showWelcome() {
        String intro = "Hello! My name is GustavoBot, but you can call me Gus\n"
                + "How may I help you today?";

        System.out.println(intro);
    }

    /**
     * Reads the input from user.
     * @return Returns user's inputs as string.
     */
    public String readCommand() {
        Scanner scanObj = new Scanner(System.in);
        String fullCommand = scanObj.nextLine();
        return fullCommand;
    }

    /**
     * Displays message to user.
     * @param message to be displayed.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }
}
