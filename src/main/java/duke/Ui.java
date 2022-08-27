package duke;

import duke.task.Task;
import java.util.Scanner;

/**
 * Ui class represents the Ui interaction with the user.
 */
public class Ui {

    /**
     * Greets the user when Duke bot starts.
     */
    public void Greet() {
        System.out.println("Hello, this is Siri! It is a pleasure to meet you!");
        System.out.println("How may I assist you?");
        printBorder();
    }

    /**
     * Bids farewell to the user after they key in bye.
     */
    public void GoodBye() {
        printBorder();
        System.out.println("So Long, farewell!");
        printBorder();
    }

    /**
     * Reads the command given by the user in the terminal.
     *
     * @param userInput The scanner which will read from the terminal.
     * @return The command that the user input in the terminal.
     */
    public String readCommand(Scanner userInput) {
        return userInput.nextLine().strip();
    }

    /**
     * Prints the border of hexes.
     */
    public void printBorder() {
        System.out.println("##############################################");
    }

    /**
     * Displays either the message of the task, the task itself and the
     * number of tasks in the list.
     *
     * @param message The message of the task.
     * @param task The task.
     * @param size The size of the task list.
     */
    public void displayCommandMessage(String message, Task task, Integer size) {
        if (message != null) {
            System.out.println(message);
        }
        if (task != null) {
            System.out.println("\t\t\t" + task);
        }
        if (size != null) {
            String numOfTasks = String.format("You currently have %d tasks in the list", size);
            System.out.println(numOfTasks);
        }
    }

    /**
     * Prints the message for the exception.
     * @param message The message for the exception.
     */
    public void showExceptionMessage(String message) {
        System.out.println(message);
    }
}
