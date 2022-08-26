package duke.utils;

import java.util.Scanner;

/**
 * Handles all the UI-related tasks.
 */

public class Ui {
    private final Scanner sc;

    /**
     * Initializes a new Ui instance.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns the string from the user.
     *
     * @return Raw input from the user.
     */
    public String readInput() {
        return sc.nextLine();
    }

    /**
     * Prints the given message with appropriate indentations and horizontal
     * lines.
     *
     * @param msg Message to be printed.
     */
    public void prettyPrint(String msg) {
        // Horizontal lines have 4 spaces as indentation
        System.out.println(
                "    ____________________________________________________________");
        String[] msgTokens = msg.split("\n");
        for (String token : msgTokens) {
            // Message has 5 spaces as indentation
            System.out.println("     " + token);
        }
        System.out.println(
                "    ____________________________________________________________\n");
    }

    /**
     * Prints Duke's greeting message.
     */
    public void sayGreetings() {
        String greetingMsg = "Hello! I'm Duke!\nWhat can I do for you?";
        prettyPrint(greetingMsg);
    }

    /**
     * Prints Duke's goodbye message.
     */
    public void sayGoodBye() {
        String goodByeMsg = "Bye. Hope to see you again soon!";
        prettyPrint(goodByeMsg);
    }

    /**
     * Closes any open resources due to Ui. This command should only be called upon exit of Duke.
     */
    public void close() {
        this.sc.close();
    }
}
