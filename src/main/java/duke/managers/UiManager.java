package duke.managers;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * TODO: Add JavaDocs
 */
public class UiManager {
    // Horizontal line separator used in beautifying print commands
    private static final String SEPARATOR = "-";
    // Denotes the number of separator symbols to used when printing the input
    private static final int SEPARATOR_SIZE = 60;
    private final Scanner scanner;
    private final PrintStream printer;

    /**
     * TODO: Add JavaDocs
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
     * TODO: Add JavaDocs
     */
    public String readCommand() {
        if (!this.scanner.hasNextLine()) {
            return "";
        }
        return this.scanner.nextLine();
    }

    public void close() {
        this.scanner.close();
    }

    /**
     * TODO: Add JavaDocs
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
