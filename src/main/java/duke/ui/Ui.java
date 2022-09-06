package duke.ui;

import java.io.PrintStream;
import java.util.Scanner;

public class Ui {

    private static final Scanner scanner = new Scanner(System.in);

    public static final String GREETING = "Hello!! I'm Duke, and I can help you " +
            "do up a simple to-do list!\nThe following commands are valid:\n" + "--------------\n"
            + "todo (creates a new todo duke.task)\n" +
            "event (creates a new event)\ndeadline (creates a new duke.task with a deadline)\n" +
            "mark (marks the completion of your tasks)\ndelete (removes a duke.task)\n" +
            "list (lists your tasks)\nbye (ends the program)";

    public static final String END_PROGRAM = "Bye. Hope to see you again soon!";
    private static final PrintStream out = System.out;

    public void showWelcomeMessage() {
        System.out.println(GREETING);
    }

    public void showExitMessage() {
        System.out.println(END_PROGRAM);
    }

    /**
     * Reads the user input.
     * @return the user command
     */
    public String getUserCommand() {
        out.print("What would you like to do: ");
        return scanner.nextLine();
    }

    /**
     * Shows the list of messages.
     * @param messages the list of messages to be shown
     */
    public void showMessages(String... messages) {
        for (String m : messages) {
            System.out.println(m);
        }
    }

    /**
     * Returns the list of messages for JavaFX processing.
     * @param messages the list of messages
     * @return message to the user in JavaFX
     */
    public String returnMessages(String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String m : messages) {
            sb.append(m);
        }
        assert !sb.toString().isEmpty() : "String must contain something!";
        return sb.toString();
    }

}
