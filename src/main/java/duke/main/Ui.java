package duke.main;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private Scanner sc;

    Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints a greeting message when the bot is opened.
     */
    public void greetingMessage() {
        String greeting = "Hello! I'm Duke\n\t" +
                "What can I do for you?";
        printMessage(greeting);
    }

    /**
     * Prints an exit message.
     */
    public void exitMessage() {
        String exit = "Bye. Hope to see you again soon!";
        printMessage(exit);
    }

    /**
     * Prints messages in the desired format.
     *
     * @param input message to be printed
     */
    public void printMessage(String input) {
        linePrint();
        System.out.println('\t' + input);
        linePrint();
    }

    /**
     * Prints a line divider.
     */
    public void linePrint() {
        System.out.println("\t____________________________________________________________");
    }

    public void showLoadingError() {
        printMessage("An error occurred when loading the file :(");
    }

    public void showError(String message) {
        printMessage(message);
    }

    /**
     * Reads user command.
     *
     * @return command entered.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Lists all the tasks stored.
     *
     * @param tasks all tasks in the list
     */
    public void listTasks(ArrayList<Task> tasks) {
        linePrint();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + tasks.get(i).toString());
        }
        linePrint();
    }
}
