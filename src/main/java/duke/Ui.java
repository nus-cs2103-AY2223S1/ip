package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Ui acts as the control center for interacting with the user.
 * Printing messages, errors and reading user input are done in this class.
 */
public class Ui {
    private static Scanner sc = new Scanner(System.in);
    private static final String LINE = "-----------------------------------------"
            + "---------------------------------------------";

    /**
     * Analyse the user input and get the command after parsing
     *
     * @param input User Input
     * @return Command after parsing
     * @throws DukeException If invalid commands
     */
    public Command run(String input) throws DukeException {
        return Parser.parse(input);
    }

    /**
     * Read user input
     *
     * @return User input
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Show message to separate commands
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Show Greetings to user
     */
    public void showGreetMessage() {
        String out = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(out);
    }

    /**
     * Show unknown command messages
     */
    public void showUnknownMessage() {
        System.out.println("I'm sorry, "
                + "but I don't know what that means :-(");
    }

    /**
     * Show add messages
     *
     * @param task Task added
     * @param size Size of TaskList
     */
    public void showAddMessage(Task task, int size) {
        String plural = size == 1
                ? "task"
                : "tasks";
        String out = "Got it. I've added this task:\n "
                + task + "\nNow you have "
                + size + " " + plural + " in the list.";
        System.out.println(out);
    }

    /**
     * Show delete messages.
     *
     * @param task Task that is going to be deleted
     * @param size Size of TaskList
     */
    public void showDeleteMessage(Task task, int size) {
        String info = task.toString();
        String plural = size - 1 <= 1
                ? "task"
                : "tasks";
        String out = "Noted. I've removed this task:\n  "
                 + info + "\n Now you have " + size
                + " " + plural + " in the list";
        System.out.println(out);
    }

    /**
     * Show exit messages when the program is going to end.
     */
    public void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Show header of list command.
     */
    public void showList() {
        System.out.println("Your List :");
    }

    /**
     * Show the details of task.
     *
     * @param index Index of task
     * @param task Task to print
     */
    public void showTask(int index, Task task) {
        System.out.println(index + "." + task.toString());
    }

    /**
     * Show mark messages.
     *
     * @param task Task marked as done
     */
    public void showMarkMessage(Task task) {
        String out = "Nice! I've marked this task as done:\n  " + task.toString();
        System.out.println(out);
    }

    /**
     * Show unmark messages.
     *
     * @param task Task unMarked as done.
     */
    public void showUnmarkMessage(Task task) {
        String out = "OK, I've marked this task as not done yet:\n  " + task.toString();
        System.out.println(out);
    }

    /**
     * Show find message when tasks are found.
     */
    public void showFindMessage() {
        System.out.println("Here are the matching tasks in your list:\n");
    }

    /**
     * Show find message when no task is found.
     */
    public void showFindEmptyMessage() {
        System.out.println("There are no matching task in your list\n");
    }

    /**
     * Show error messages.
     *
     * @param message Error message
     */
    public void showLoadingError(String message) {
        System.out.println(message);
    }

    /**
     * Show message when file is not found
     * @param message Error message
     */
    public void showFileNotFound(String message) {
        System.out.println(message);
    }

    /**
     * Show message when file is being read
     */
    public void showReadMessage() {
        System.out.println("Reading files and storing tasks into TaskList");
    }
}
