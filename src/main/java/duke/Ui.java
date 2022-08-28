package duke;

import java.io.PrintStream;

public class Ui {
    private final PrintStream OUTPUT = System.out;

    public void printDivider() {
        OUTPUT.println("------------------------------");
    }

    public void printWithDivider(String s) {
        this.printDivider();
        OUTPUT.printf("%s\n", s);
        this.printDivider();
        OUTPUT.print("\n");
    }

    public void println(String s) {
        OUTPUT.println(s);
    }

    public void showWelcome() {
        this.printWithDivider("Hello! I'm duke.Duke\nWhat can I do for you?");
    }

    public void showLoadingError() {
        this.printWithDivider(
                "There was a problem loading the tasks from the output file. Starting with empty list.");
    }
}
