package duke.main;

import java.util.Scanner;

public class Ui {
    Scanner scanner;

    public Ui(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readInput() {
        return scanner.nextLine();
    }

    /**
     * Sends a greeting to the user
     *
     */
    public static void greet() {
        System.out.println("Hello! I'm BotChat123 \nWhat can I do for you?");
    }

    /**
     * Terminates the conversation with the user
     *
     */
    public static void bye(){
        System.out.println("Bye. Please chat with me again!");
    }

    public static void list(TaskList taskList) {
        for (int i = 0; i < taskList.length(); i++) {
                System.out.println(i + 1 + ": " + taskList.getTask(i));
            }
    }

    public void repeater(String task) {
        System.out.println(task);
    }
}
