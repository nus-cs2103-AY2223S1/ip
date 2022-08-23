package duke.util;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class UI {
    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final Scanner inputStreamScanner;
    private final PrintStream printStream;

    public UI(InputStream inputStream, PrintStream printStream) {
        this.inputStreamScanner = new Scanner(inputStream);
        this.printStream = printStream;
    }

    public void greet() {
        printStream.println(LOGO);
        print("Hi! I'm Duke. What can I do for you?");
    }

    public void print(String message) {
        printStream.println(">> " + message);
    }

    public String read() {
        printStream.print("<< ");
        return inputStreamScanner.nextLine();
    }

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

    public void exit() {
        print("Bye! Hope to see you soon!");
    }
}
