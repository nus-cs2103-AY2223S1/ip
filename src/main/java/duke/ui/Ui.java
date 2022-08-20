package duke.ui;

import java.io.PrintWriter;
import java.util.Scanner;

public class Ui {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private Scanner scanner;
    private PrintWriter writer;

    public Ui() {
        scanner = new java.util.Scanner(System.in);
        writer = new PrintWriter(System.out);
    }

    public void showWelcome() {
        writer.println("Hello from\n" + Ui.LOGO);
        writer.println("What can I do for you?\n");
        writer.flush();
    }

    public void showLine() {
        this.showOutput("____________________________________________________________\n");
    }

    public void showError(String error) {
        writer.println(error);
        writer.flush();
    }

    public void showOutput(String output) {
        writer.print(output);
        writer.flush();
    }

    public String readCommand() {
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            if (!nextLine.trim().isBlank()) {
                return nextLine;
            }
        }
        return "";
    }
}
