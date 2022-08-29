package duke;

import duke.tasks.Task;

import java.util.Scanner;

/**
 * Handles interaction with user.
 */
public class Ui {
    /**
     * Prints line format.
     */
    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Greets user.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        showLine();
        System.out.println("     Hello! I'm Duke\n"
                + "     What can I do for you?");
        showLine();
    }

    public String readCommand() {
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        return userInput;
    }

    /**
     * Prints Task information when added.
     *
     * @param task Task that was added.
     * @param size Current number of tasks in list.
     *
     */
    public void printAddTask(Task task, int size) {
        System.out.println("     Got it. I've added this task:\n"
                + "       " + task.toString() + "\n"
                + "     Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints upon deletion of Task.
     *
     * @param task Task deleted.
     * @param size Remaining number of Tasks in list.
     */
    public void printDeleteTask(String task, int size) {
        System.out.println("     Noted. I've removed this task:\n"
                + "       " + task + "\n"
                + "     Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints list of Tasks currently.
     *
     * @param list The list of Tasks in String.
     */
    public void printDisplayList(String list) {
        String message = "     Here are the tasks in your list:\n";
        message += list;
        System.out.println(message);
    }

    /**
     * Prints when task is marked.
     *
     * @param message The Task that was marked in String.
     */
    public void printMarkTask(String message) {
        System.out.println("     Nice! I've marked this task as done:\n"
                + "       " + message);
    }

    /**
     * Prints when task is unmarked.
     *
     * @param message The Task that was unmarked in String.
     */
    public void printUnmarkTask(String message) {
        System.out.println("     OK, I've marked this task as not done yet:\n"
                + "       " + message);
    }

    /**
     * Prints Tasks that matched description given.
     *
     * @param list Tasks that matched description given.
     */
    public void printFindTask(String list) {
        String message = "     Here are the matching tasks in your list:\n";
        message += list;
        System.out.println(message);
    }

    /**
     * Prints error message.
     *
     * @param e Exception caught.
     */
    public void printError(Exception e) {
        System.out.println("     " + e.getMessage());
    }

    /**
     * Prints when program terminates.
     */
    public void printExit() {
        System.out.println("     Bye. Hope to see you again soon!");
    }
}
