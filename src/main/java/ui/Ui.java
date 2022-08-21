package ui;

import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private final Scanner scanner;
    private final PrintStream printer;

    // Horizontal line separator used in beautifying print commands
    private static final String SEPARATOR = "-";
    // Denotes the number of separator symbols to used when printing the input
    private static final int SEPARATOR_SIZE = 60;

    public Ui() {
        this.scanner = new Scanner(System.in);
        this.printer = System.out;
    }

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
     * Returns a string consisting of the separator symbol repeated for a specified number of times
     *
     * @return Separator symbol repeated for a specified number of times
     */
    private static String getSeparatorLine() {
        return Ui.SEPARATOR.repeat(Ui.SEPARATOR_SIZE);
    }

    public void print(String input) {
        this.printer.println("\t" + Ui.getSeparatorLine());
        String[] inputs = input.split("\n");
        for (String value : inputs) {
            this.printer.println("\t" + value);
        }
        this.printer.println("\t" + Ui.getSeparatorLine());
    }
}
