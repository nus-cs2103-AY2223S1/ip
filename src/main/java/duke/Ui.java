package duke;

import java.util.Scanner;

/** Handles terminal input ouput user interaction */
public class Ui {
    public static final String DIVIDER = "____________________________________________________________";

    private String greetingMessage = "Hello! I'm Duke\nWhat can I do for you?";
    private String farewellMessage = "Bye. Hope to see you again soon!";

    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String getUserInput() {
        return this.scanner.nextLine();
    }

    public void printDivider() {
        System.out.println(this.DIVIDER);
    }

    /**
     * Shows message(s) to the user.
     *
     * @param messages Message(s) to be shown.
     */
    public void printMessage(String... messages) {
        printDivider();
        for (String message: messages) {
            System.out.print(message);
        }
        printDivider();
    }

    /**
     * Shows greeting message to the user.
     *
     */
    public void printGreetingMessage() {
        printDivider();
        System.out.println(this.greetingMessage);
        printDivider();
    }

    /**
     * Shows farewell message to the user.
     *
     */
    public void printFarewellMessage() {
        printDivider();
        System.out.println(this.farewellMessage);
        printDivider();
    }
}
