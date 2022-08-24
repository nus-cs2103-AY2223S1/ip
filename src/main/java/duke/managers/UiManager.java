package duke.managers;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Encapsulates the logic for reading keyboard input and writing to screen display.
 *
 * @author Emily Ong Hui Qi
 */
public class UiManager {
    /**
     * Horizontal line separator used in beautifying print commands
     */
    private static final String SEPARATOR = "-";

    /**
     * Denotes the number of separator symbols to used when printing the input
     */
    private static final int SEPARATOR_SIZE = 60;

    private final Scanner scanner;
    private final PrintStream printer;

    /**
     * Initializes the scanner and printer objects used for reading keyboard inputs and writing to screen display.
     */
    public UiManager() {
        this.scanner = new Scanner(System.in);
        this.printer = System.out;
    }

    /**
     * Returns a string consisting of the separator symbol repeated for a specified number of times
     *
     * @return Separator symbol repeated for a specified number of times
     */
    private static String getSeparatorLine() {
        return UiManager.SEPARATOR.repeat(UiManager.SEPARATOR_SIZE);
    }

    /**
     * Returns the input on the entire line received from the keyboard.
     *
     * @return Input on the entire line received from the keyboard
     */
    public String readCommand() {
        if (!this.scanner.hasNextLine()) {
            return "";
        }
        return this.scanner.nextLine();
    }

    /**
     * Closes the scanner object to prevent it from reading any further inputs.
     */
    public void close() {
        this.scanner.close();
    }

    /**
     * Writes the given input to screen display.
     *
     * @param input The given input to be written to screen display
     */
    public void print(String input) {
        this.printer.println("\t" + UiManager.getSeparatorLine());
        String[] inputs = input.split("\n");
        for (String value : inputs) {
            this.printer.println("\t" + value);
        }
        this.printer.println("\t" + UiManager.getSeparatorLine());
    }
}
