package duke;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private static final String GREETING = "Hello! I'm Duke\nWhat can I do for you?";
    private final Scanner scanner;
    private final PrintStream out;

    Ui(InputStream in, PrintStream out) {
        this.scanner = new Scanner(in);
        this.out = out;
    }

    String getUserCommand() {
        String nextCmdLine = scanner.nextLine();

        while (shouldIgnoreLine(nextCmdLine)) {
            nextCmdLine = scanner.nextLine();
        }
        return nextCmdLine;
    }

    private boolean shouldIgnoreLine(String line) {
        return line.trim().isEmpty() || line.contains("//");
    }

    /**
     * Start message of Duke
     */
    public void startMessage() {
        System.out.println(GREETING);
    }
}
