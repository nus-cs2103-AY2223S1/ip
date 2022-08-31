package duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * A class that provides a simple user interface for the Duke system.
 */
public class Io {
    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final Scanner inputStreamScanner;
    private final PrintStream printStream;

    /**
     * Creates a new UI with the given input stream and print stream.
     *
     * @param inputStream The input stream to use for user input.
     * @param printStream The print stream to use for output.
     */
    public Io(InputStream inputStream, PrintStream printStream) {
        this.inputStreamScanner = new Scanner(inputStream);
        this.printStream = printStream;
    }

    /**
     * Greets the user.
     */
    public void greet() {
        printStream.println(LOGO);
        print("Hi! I'm Duke. What can I do for you?");
    }

    /**
     * Prints a message.
     *
     * @param message The message to print.
     */
    public void print(String message) {
        printStream.println(">> " + message);
    }

    /**
     * Reads a line from the input stream.
     *
     * @return Next line from the input stream.
     */
    public String read() {
        printStream.print("<< ");
        return inputStreamScanner.nextLine();
    }

    /**
     * Read a y/n response.
     * Keeps asking for a y/n response if something else is provided.
     *
     * @param prompt The prompt to print before reading response.
     * @return {@code true} if the user enter y, else {@code false}.
     */
    public boolean readYesNoResponse(String prompt) {
        print(prompt + " (y/n)");
        while (true) {
            String response = read().strip();
            if (response.equals("y")) {
                return true;
            } else if (response.equals("n")) {
                return false;
            } else {
                print("Invalid input. Please enter y/n.");
            }
        }
    }

    /**
     * Ends the user interaction.
     */
    public void exit() {
        print("Bye! Hope to see you soon!");
    }
}
