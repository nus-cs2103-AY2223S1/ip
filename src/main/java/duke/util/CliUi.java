package duke.util;

import java.util.Scanner;

/**
 * A class that is responsible for reading user input and printing computed output on the command line interface (CLI).
 * It is the frontend of the application.
 */
public class CliUi {
    private static final String HORIZONTAL_BAR = "-------------------------";
    private static final String INDENTATION = "    ";
    private final Scanner scanner;

    /**
     * Returns a new instance of Ui that makes use of a scanner reading the standard system input.
     */
    public CliUi() {
        scanner = new Scanner(System.in);
    }

    private static String formatOutput(String output) {
        return HORIZONTAL_BAR + '\n' + INDENTATION + output + '\n' + HORIZONTAL_BAR + '\n' + '\n';
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
