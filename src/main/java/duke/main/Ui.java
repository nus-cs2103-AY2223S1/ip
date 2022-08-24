package duke.main;

import java.util.Scanner;

public class Ui {
    /** sc takes in user input infinitely until Duke is closed */
    private Scanner sc = new Scanner(System.in);

    public void showLoadingError() {
        System.out.println("Duke will overwrite the storage file for you.");
    }

    /**
     * Prints out greeting when user just opened Duke
     */
    public void showWelcome() {
        String logo = " ____        _        \n" +
                "|  _ \\ _   _| | _____ \n" +
                "| | | | | | | |/ / _ \\\n" +
                "| |_| | |_| |   <  __/\n" +
                "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
    }

    public void showLine() {
        System.out.println("----------------------------------");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showError(String message) {
        System.out.println(message);
    };
}
