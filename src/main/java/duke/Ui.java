package duke;

import java.util.Scanner;

public class Ui {
    private Scanner sc = new Scanner(System.in);

    public void showWelcome() {
        String welcomeMessage = "Hi I'm Duke!";
        printString(welcomeMessage);
    }

    public void showError(String error) {
        printString(error);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println("----------------------------------");
    }

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
