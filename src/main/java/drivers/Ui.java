package drivers;

import java.util.Scanner;

/**
 * Deals with interactions from the user.
 */
public class UI {
    private Scanner sc;

    /**
     * Constructor for the UI class.
     */
    public UI() {
        sc = new Scanner(System.in);
    }

    /**
     * Greeting message to the user during chat-bot startup.
     */
    public void greeting() {
        String greetingMessage = "Hi! I am Tumu. Nice to meet you!\n" +
                "\tWhat is on your mind today?\n";

        notifyUser(greetingMessage);
    }

    /**
     * Shows the line to the user indicating response from the program.
     */
    public void showLine() {
        notifyUser("_".repeat(60));
    }

    /**
     * Where all the messages seen by the user will pass through.
     * @param message The message to be printed, after additional
     *                formatting.
     */
    public void notifyUser(String message) {
        System.out.println("\t" + message);
    }

    /**
     * Reads the command from the user.
     * @return Full command from the user.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Close the scanner. Prevents further input from the user.
     */
    public void closeReader() {
        sc.close();
    }
}
