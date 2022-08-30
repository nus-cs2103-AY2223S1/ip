package duke.main;

import java.util.Scanner;

/**
 * Ui handles text output to user
 */
public class Ui {

    /**
     * sc takes in user input infinitely until Duke is closed
     */
    private final Scanner sc = new Scanner(System.in);

    public void showLoadingError() {
        System.out.println("Duke will overwrite the storage file for you.");
    }

    /**
     * Prints out greeting when user just opened Duke
     */
    public String showWelcome() {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        String str = "Hello from\n" + logo;
        return str += "Hello! I'm Duke\nWhat can I do for you?\n";
    }

    /**
     * Shows line to divide commands
     */
    public void showLine() {
        System.out.println("----------------------------------");
    }

    /**
     * Reads command from user
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Shows error to user
     */
    public void showError(String message) {
        System.out.println(message);
    }
}
