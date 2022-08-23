package duke;

import java.util.Scanner;

/**
 * Handle all user interaction .
 */
public class Ui {
    public static final String LINE = "_____________________";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public void showLoadingError() {
        System.out.println("Failed to load tasks from storage.");
    }
    private final Scanner sc = new Scanner(System.in);

    /**
     * Display the given text.
     *
     * @param text The text to display.
     */
    public void displayText(String text) {
        System.out.println(text);
    }

    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Displays the specified text defined by its format and arguments.
     *
     * @param format A format string
     * @param args   Arguments referenced by the format specifiers in the format string.
     */
    public void displayText(String format, Object... args) {
        displayText(String.format(format, args));
    }

    /**
     * Display the welcome message.
     */
    public void showWelcome() {
        showLine();
        String name = "Duke";
        displayText("Hello! I'm %s\nWhat do you need to do?", name);
        showLine();
    }

    public void showGoodbye() {
        displayText("Bye. Hope to see you again soon!");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showError(String message) {
        displayText(ANSI_RED + message + ANSI_RESET);
    }
}
