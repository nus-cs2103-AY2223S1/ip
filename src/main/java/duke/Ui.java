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
    public String print(String message) {
        return message;
    }

    /**
     * Prints a message containing the given size.
     *
     * @param size The size to be printed
     */
    public String printSizeOfList(int size) {
        return String.format("Now you have %d tasks in the list", size);
    }

    /**
     * Prints the message to be shown to the user after marking a Task.
     *
     * @param task The Task that was marked
     */
    public String printMarkTask(String task) {
        return "Nice! I've marked this task as done:" + task;
    }

    /**
     * Prints the message to be shown to the user after unmarking a Task.
     *
     * @param task The Task that was unmarked
     */
    public String printUnmarkTask(String task) {
        return "OK, I've marked this task as not done yet:" + task;
    }

    /**
     * Prints the message to be shown to the user after deleting a Task from the TaskList.
     *
     * @param task The Task that was deleted from the TaskList
     */
    public String printDeleteTask(String task, int size) {
        return "Noted. I've removed this task:\n" + task + "\n" +printSizeOfList(size);
    }

    /**
     * Prints the message to be shown to the user after adding a Task to the TaskList.
     *
     * @param task The Task that was added to the TaskList
     */
    public String printAddTask(String task, int size) {
        return "Got it. I've added this task:\n" + task + "\n" + printSizeOfList(size);
    }

    public String printSearchedList(String searchedList) {
        return "Here are the matching tasks in your list:" + "\n" + searchedList;
    }

    public String printByeCommand() {
        return "Bye. Hope to see you again soon!";
    }
}
