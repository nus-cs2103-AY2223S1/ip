package duke.main;

import java.util.Scanner;

/**
 * Represents the UI that is used for the interaction with the user
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructor for UI
     *
     * @param scanner
     */
    public Ui(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * readInput method that reads the input from the user
     *
     * @return String
     */
    public String readInput() {
        return scanner.nextLine();
    }

    /**
     * greet method that Sends a greeting to the user
     *
     */
    public static void greet() {
        System.out.println("Hello! I'm BotChat123 \nWhat can I do for you?");
    }

    /**
     * bye method that is sent on termination of conversation with the user
     *
     */
    public static void bye() {
        System.out.println("Bye. Please chat with me again!");
    }

    /**
     * list method that lists out the tasks in task list
     *
     * @param taskList
     */
    public static void list(TaskList taskList) {
        for (int i = 0; i < taskList.length(); i++) {
            System.out.println(i + 1 + ": " + taskList.getTask(i));
        }
    }

    /**
     * repeater method that repeats the string given to it.
     *
     * @param task
     */
    public void repeater(String task) {
        System.out.println(task);
    }
}
