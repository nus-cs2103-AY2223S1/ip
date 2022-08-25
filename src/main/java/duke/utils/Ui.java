package duke.utils;

import java.util.Scanner;

/**
 * Handles the inputs and outputs shown to the user.
 */
public class Ui {
    public String takeInput() {
        Scanner input = new Scanner(System.in);
        String inputText = input.nextLine();
        return inputText;
    }

    /**
     * Prints Duke's greeting to the user.
     */
    public void printGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Prints Duke's goodbye to the user.
     */
    public void printBye() {
        System.out.println("Goodbye. Hope to see you again soon!");
    }

}
