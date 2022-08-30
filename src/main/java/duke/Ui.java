package duke;

import java.util.Scanner;

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

    public void printMessage(String... messages) {
        printDivider();
        for(String message: messages) {
            System.out.print(message);
        }
        printDivider();
    }

    public void printGreetingMessage() {
        printDivider();
        System.out.println(this.greetingMessage);
        printDivider();
    }

    public void printFarewellMessage() {
        printDivider();
        System.out.println(this.farewellMessage);
        printDivider();
    }
}
