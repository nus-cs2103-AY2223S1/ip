package drake;

import java.util.Scanner;

/**
 * Interact with the user.
 */
public class Ui {
    private final String DASH = "------------------------------------------------------";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private final Scanner sc;

    /**
     * Constructor.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Greets the user by printing the welcome message.
     */
    public void showWelcome() {
        System.out.println(DASH);
        System.out.println("You used to call me on my cellphone");
        StringBuilder logo = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            logo.append("DRAKE ".repeat(4));
            logo.append("DRAKE");
            if (i == 4) break;
            logo.append("\n");
        }
        System.out.println(logo);
        System.out.println("!@#$%^&*()-+!@#$%^&*()`~`!@#$");
        System.out.println("drake.Drake's (me) the kind of guy to help you out uwu");
        System.out.println("Go ahead, make that hotline bling");
        System.out.println(DASH);
    }

    /**
     * Reads input from the console into a String.
     *
     * @return The trimmed input line as a String.
     */
    public String readInput() {
        return sc.nextLine().trim();
    }

    /**
     * Prints the given line into the console.
     *
     * @param line The line to print.
     */
    public void printLine(Object line) {
        System.out.println(line);
    }

    /**
     * Prints the exit message.
     *
     */
    public void printBye() {
        System.out.println("I'm down for you always. See you " + ANSI_RED + "<3" + ANSI_RESET);
    }

    /**
     * Prints a dash.
     */
    public void printDash() {
        System.out.println(DASH);
    }

    /**
     * Prints the given error message with special formatting.
     *
     * @param errorMessage The given error message.
     */
    public void printError(String errorMessage) {
        System.out.println(ANSI_RED + errorMessage + ANSI_RESET);
    }
}
