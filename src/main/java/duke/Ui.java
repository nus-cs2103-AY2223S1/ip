package duke;

import java.util.Scanner;

public class Ui {

    private final Scanner scanner = new Scanner(System.in).useDelimiter("\n");


    public Ui() {

    }

    public static String showWelcomeMessage() {
        return "Welcome to Duke!";
    }

    public static String showGoodbyeMessage() {
        return "Goodbye!";
    }

    public static String showInvalidCommandError() {
        return "Invalid command.";
    }

    public String getUserCommand() {
        return scanner.next();
    }
}
