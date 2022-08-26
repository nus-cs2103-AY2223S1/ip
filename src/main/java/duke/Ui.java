package duke;

import java.util.Scanner;

public class Ui {

    protected Scanner scanner;

    /**
     * Handles all system output messages.
     */

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public void goodBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("_".repeat(50));
    }

    public void showLoadingError(String errorMessage) {
        System.out.printf("UI Error! %s\n", errorMessage);
    }

    public void showError(String errorMessage) {
        System.out.printf("UI Error! %s\n", errorMessage);
    }
}
