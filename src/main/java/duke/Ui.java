package duke;

import java.util.Scanner;

public class Ui {

    private final Scanner scanner = new Scanner(System.in).useDelimiter("\n");


    public Ui() {

    }

    public void showWelcomeMessage() {
        System.out.println("Welcome to Duke!");
    }

    public void showGoodbyeMessage() {
        System.out.println("Goodbye!");
    }

    public void showInvalidCommandError() {
        System.out.println("Invalid command.");
    }

    public String getUserCommand() {
        return scanner.next();
    }
}
