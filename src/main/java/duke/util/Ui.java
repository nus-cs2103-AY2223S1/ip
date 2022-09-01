package duke.util;

import java.util.Scanner;

/**
 * Handles the interactions with the user.
 *
 * @author bensohh
 * @version CS2103T AY 22/23 Sem 1 (G01)
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructor to create an instance of Ui.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * A welcome message for the user.
     *
     * @return String representation of a Welcome Message
     */
    public String welcomeMessage() {
        String logo = " _           _        \n"
                + "| |    _   _| | _____ \n"
                + "| |   | | | | |/ / _ \\\n"
                + "| |___| |_| |   <  __/\n"
                + "|_____|\\__,_|_|\\_\\___|\n";

        return "________________________________________\n"
                + "Hola Amigo! My name is\n"
                + logo
                + "\n"
                + "How may I assist you today?\n"
                + "________________________________________";
    }

    /**
     * Displays the welcome message.
     */
    public void displayWelcome() {
        System.out.println(this.welcomeMessage());
    }

    /**
     * Reads the next line of user input.
     *
     * @return String representation of the next line of user input
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Prints out a divider for formatting.
     */
    public void showLine() {
        System.out.println("________________________________________");
    }

    /**
     * Displays the error messages.
     *
     * @param errorMessage String representation of the error message to be shown
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
