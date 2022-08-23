package sky;

import java.util.Scanner;

/**
 * The Ui class deals with interaction with the user.
 */
public class Ui {
    /**
     * Prints out the greeting message.
     */
    public void greetUser() {
        System.out.println("  ____________________________________________________________");
        System.out.println("  Hello from Sky!\n  Your heavenly chatbot to help you track your things.");
        System.out.println("  ____________________________________________________________");
    }

    /**
     * Prints out a line separator
     */
    public void showLine() {
        System.out.println("  ____________________________________________________________");
    }

    /**
     * Returns everything the user typed within a line in the form of a String.
     *
     * @param scanner Scans the standard input for user input.
     * @return
     */
    public String readCommand(Scanner scanner) {
        String fullCommand = scanner.nextLine();
        return fullCommand;
    }

    /**
     * Prints out the ending message.
     */
    public void endConvo() {
        System.out.println("  Bye. May all your endeavours fly high!");
    }

    /**
     * Prints out the specified input text.
     *
     * @param s The input text
     */
    public void displayText(String s) {
        System.out.println(s);
    }
}
