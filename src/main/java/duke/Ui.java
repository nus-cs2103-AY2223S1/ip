package duke;

public class Ui {
    protected void showLoadingError() {
        System.out.println("Failed to load tasks from storage.");
    }

    /**
     * Display the given text.
     *
     * @param text The text to display.
     */
    protected void displayText(String text) {
        String line = "_".repeat(20) + '\n';
        System.out.println(line);
        System.out.println(text);
        System.out.println(line);
    }

    /**
     * Displays the specified text defined by its format and arguments.
     *
     * @param format A format string
     * @param args   Arguments referenced by the format specifiers in the format string.
     */
    protected void displayText(String format, Object... args) {
        displayText(String.format(format, args));
    }

    protected void showWelcome() {
        String name = "Duke";
        displayText("Hello! I'm %s\nWhat do you need to do?", name);
    }

    protected void showGoodbye() {
        displayText("Bye. Hope to see you again soon!");
    }
}
