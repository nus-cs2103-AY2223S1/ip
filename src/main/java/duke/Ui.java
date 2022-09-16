package duke;

import java.sql.SQLOutput;
import java.util.Scanner;

/**
 * Public class for a Ui, which is responsible for printing the output to the user.
 *
 * @author kaij77
 * @version 0.1
 */
public class Ui {
    private Scanner scanner;

    /**
     * Public constructor for a Ui.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the user input using the Scanner.
     *
     * @return The user's String input
     */
    public String read() {
        return this.scanner.nextLine();
    }

    /**
     * Prints the given message.
     *
     * @param message The message to be printed
     */
    public void print(String message) {
        System.out.println(message);
    }

    /**
     * Prints a message containing the given size.
     *
     * @param size The size to be printed
     */
    public void printSizeOfList(int size) {
        System.out.println(String.format("Now you have %d tasks in the list", size));
    }

    /**
     * Prints the message to be shown to the user after marking a Task.
     *
     * @param task The Task that was marked
     */
    public void printMarkTask(String task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    /**
     * Prints the message to be shown to the user after unmarking a Task.
     *
     * @param task The Task that was unmarked
     */
    public void printUnmarkTask(String task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
    }

    /**
     * Prints the message to be shown to the user after deleting a Task from the TaskList.
     *
     * @param task The Task that was deleted from the TaskList
     */
    public void printDeleteTask(String task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
    }

    /**
     * Prints the message to be shown to the user after adding a Task to the TaskList.
     *
     * @param task The Task that was added to the TaskList
     */
    public void printAddTask(String task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
    }
}
