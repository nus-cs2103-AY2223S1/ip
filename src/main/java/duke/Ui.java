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

    /**
     * Analyse the user input and get the command after parsing
     *
     * @param input User Input
     * @return Command after parsing
     * @throws DukeException If invalid commands
     */
    public Command run(String input) throws DukeException {
        Parser parser = new Parser();
        return parser.parse(input);
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
        System.out.println("--------------------------------------------------------------------------------------");
    }

    /**
     * Show Greetings to user
     */
    public void showGreetMsg() {
        String out = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(out);
    }

    /**
     * Show unknown command messages
     */
    public void showUnknownMsg() {
        System.out.println("I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Show add messages
     *
     * @param task Task added
     * @param cnt Size of TaskList
     */
    public void showAddMsg(Task task, int cnt) {
        String plural = cnt == 1
                ? "task"
                : "tasks";

        String out = "Got it. I've added this task:\n "
                + task + "\nNow you have "
                + cnt + " " + plural + " in the list.";
        System.out.println(out);
    }

    /**
     * Show delete messages.
     *
     * @param task Task that is going to be deleted
     * @param size Size of TaskList
     */
    public void showDeleteMsg(Task task, int size) {
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
    public void showExitMsg() {
        String out = "Bye. Hope to see you again soon!";
        System.out.println(out);
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
    public void showMarkMsg(Task task) {
        String out = "Nice! I've marked this task as done:\n  " + task.toString();
        System.out.println(out);
    }

    /**
     * Show unmark messages.
     *
     * @param task Task unMarked as done.
     */
    public void showUnmarkMsg(Task task) {
        String out = "OK, I've marked this task as not done yet:\n  " + task.toString();
        System.out.println(out);
    }

    /**
     * Show error messages.
     *
     * @param msg Error message
     */
    public void showLoadingError(String msg) {
        System.out.println(msg);
    }

    /**
     * Show message when file is not found
     * @param msg Error message
     */
    public void showFileNotFound(String msg) {
        System.out.println(msg);
    }
}
