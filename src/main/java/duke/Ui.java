package duke;

import java.util.Scanner;

/**
 * UI for interaction with user.
 *
 * @author Aaron Pang
 * @version CS2103T AY22/23 Sem 1
 */
public class Ui {
    private static final String LINE = "_________" ;

    private Scanner input;

    public Ui() {
        this.input = new Scanner(System.in);
    }

    /**
     * Shows the Welcome message.
     */
    public static void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What may I do for you?");
    }

    /**
     * Reads the next line input by user.
     * @return input of user.
     */
    public String readCommand() {
        return input.nextLine();
    }

    /**
     * Shows the separation line.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Shows the goodbye message.
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
        input.close();
    }

    /**
     * Shows the loading error message.
     */
    public void showLoadingError() {
        System.out.println("No saved data found.");
    }

    /**
     * Shows the error message.
     * @param message The error message.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Shows the find message.
     */
    public void showFind() {
        System.out.println("Here are the matching tasks in your list:");
    }
}

