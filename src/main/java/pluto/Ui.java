package pluto;

import java.util.Scanner;

/**
 * Display appropriate messages to the user.
 */
public class Ui {
    /** Name of chat bot */
    private static final String CHATBOT = "Pluto";
    /** To display output in red color */
    private static final String ANSI_RED = "\u001B[31m";
    /** To reset display color of the output */
    private static final String ANSI_RESET = "\u001B[0m";
    /** Scanner to take inputs */
    private Scanner sc;

    /**
     * Constructor that initializes global variables.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Displays welcome message.
     */
    public void showWelcome() {
        String introduction = String.format("\tHello I am %s.\n\tWhat can I do for you?", CHATBOT);
        System.out.println(introduction);
    }

    /**
     * Reads command from the user.
     * @return Command string.
     */
    public String readCommand() {
        return sc.nextLine().strip();
    }

    /**
     * Displays output to the user.
     * @param message Message to be displayed.
     */
    public void print(StringBuilder message) {
        System.out.println(message);
    }

    /**
     * Displays output to the user.
     * @param message Message to be displayed.
     */
    public void print(String message) {
        System.out.println(message);
    }

    /**
     * Display error messages.
     * @param emessage Error message.
     */
    public void showError(String emessage) {
        System.out.println(ANSI_RED + emessage + ANSI_RESET);
    }
}
