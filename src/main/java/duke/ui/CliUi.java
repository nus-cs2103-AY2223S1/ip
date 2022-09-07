package duke.ui;

import static duke.Duke.TAB;

import java.util.Scanner;

/**
 * A class that is responsible for reading user input and printing computed output on the command line interface (CLI).
 * It is the frontend of the application.
 */
public class CliUi implements Formatter {
    private static final String HORIZONTAL_BAR = "-------------------------";
    private final Scanner scanner;

    /**
     * Returns a new instance of Ui that makes use of a scanner reading the standard system input.
     */
    public CliUi() {
        scanner = new Scanner(System.in);
    }

    /**
     * Takes in a raw string and formats it.
     *
     * @param input String that is raw.
     * @return String formatted and is about to be printed on some screen output.
     */
    public String formatOutput(String input) {
        assert(input != null);
        return HORIZONTAL_BAR + '\n' + TAB + input + '\n' + HORIZONTAL_BAR + '\n' + '\n';
    }

    /**
     * Reads user input line by line. If the user has not entered anything yet, it will wait.
     *
     * @return A string that carries what the user types.
     */
    public String readInput() {
        return scanner.nextLine();
    }

    /**
     * Formats and prints a message passed from other computational process.
     *
     * @param output The message to be formatted and printed on the screen.
     */
    public void printOutput(String output) {
        System.out.println(formatOutput(output));
    }
}
