package duke;

import java.util.Scanner;

/**
 * Handles displaying content to the user.
 */
public class Ui {
    private Scanner sc = new Scanner(System.in);

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        String welcomeMessage = "Hi I'm Duke!";
        printString(welcomeMessage);
    }

    /**
     * Displays an error message to the user.
     * 
     * @param error The error message to be displayed.
     */
    public void showError(String error) {
        printString(error);
    }

    /**
     * Obtains user input via System.in.
     * 
     * @return Raw input from the user in String format.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays a line break for readability.
     */
    public void showLine() {
        System.out.println("----------------------------------");
    }

    /**
     * Displays custom input between line breaks.
     * 
     * @param input
     */
    public void printString(String input) {
        // String indentedInput = input.replaceAll("(?m)^", "\t");
        // System.out.println("\t----------------------------------");
        System.out.println("----------------------------------");
        // System.out.println(indentedInput);
        System.out.println(input);
        System.out.println("----------------------------------");
        // System.out.println("\t----------------------------------");
    }
}
