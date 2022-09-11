package pluto;

import java.util.Scanner;

/**
 * Display appropriate messages to the user.
 */
public class Ui {
    /** Name of chat bot */
    private static final String CHATBOT = "Pluto";
    /** To display output in red color */
    private static final String ANSI_RED = "";
    /** To reset display color of the output */
    private static final String ANSI_RESET = "";
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
    public static String showWelcome() {
        String introduction = String.format("Hello I am %s.\nWhat can I do for you?", CHATBOT);
        return introduction;
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
    public String print(StringBuilder message) {
        return message.toString();
    }

    /**
     * Displays output to the user.
     * @param message Message to be displayed.
     */
    public String print(String message) {
        return message;
    }

    /**
     * Display error messages.
     * @param emessage Error message.
     */
    public String showError(String emessage) {
        return ANSI_RED + emessage + ANSI_RESET;
    }
}
