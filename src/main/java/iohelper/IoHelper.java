package iohelper;

//import util
import java.util.Scanner;

/**
 * Handles the input and output in the CLI
 */
public class IoHelper {
    private final Scanner scanner;
    private String text;

    /**
     * Constructs IoHelper object and initialise a Scanner object.
     */
    public IoHelper() {
        scanner = new Scanner(System.in);
    }

    private void scan() {
        text = scanner.nextLine();
    }

    /**
     * Scans the CLI and returns the text input by user.
     *
     * @return text input by user.
     */
    public String getText() {
        scan();
        return text;
    }

    /**
     * Closes the scanner object.
     */
    public void closeScanner() {
        scanner.close();
    }

    /**
     * Output any messages passed in as parameter.
     *
     * @param message Object to be output in String representation to the CLI.
     */
    public void print(Object message) {
        System.out.println(message);
    }
}
