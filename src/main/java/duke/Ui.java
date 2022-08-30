package duke;

import duke.tasks.Task;

import java.util.Scanner;

/**
 * Handles interaction with user.
 */
public class Ui {
//    /**
//     * Prints line format.
//     */
//    public String showLine() {
//        return ("    ____________________________________________________________");
//    }

    /**
     * Greets user.
     */
    public static String showWelcome() {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        String result = ("Hello from\n" + logo);
        String result = ("     Hello! I'm duke.Duke\n"
                + "     What can I do for you?");
        return result;
    }

    /**
     * Prints Task information when added.
     *
     * @param task Task that was added.
     * @param size Current number of tasks in list.
     *
     */
    public String printAddTask(Task task, int size) {
        return ("     Got it. I've added this task:\n"
                + "       " + task.toString() + "\n"
                + "     Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints upon deletion of Task.
     *
     * @param task Task deleted.
     * @param size Remaining number of Tasks in list.
     */
    public String printDeleteTask(String task, int size) {
        return ("     Noted. I've removed this task:\n"
                + "       " + task + "\n"
                + "     Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints list of Tasks currently.
     *
     * @param list The list of Tasks in String.
     */
    public String printDisplayList(String list) {
        String message = "     Here are the tasks in your list:\n";
        message += list;
        return (message);
    }

    /**
     * Prints when task is marked.
     *
     * @param message The Task that was marked in String.
     */
    public String printMarkTask(String message) {
        return ("     Nice! I've marked this task as done:\n"
                + "       " + message);
    }

    /**
     * Prints when task is unmarked.
     *
     * @param message The Task that was unmarked in String.
     */
    public String printUnmarkTask(String message) {
        return ("     OK, I've marked this task as not done yet:\n"
                + "       " + message);
    }

    /**
     * Prints Tasks that matched description given.
     *
     * @param list Tasks that matched description given.
     */
    public String printFindTask(String list) {
        String message = "     Here are the matching tasks in your list:\n";
        message += list;
        return (message);
    }

    /**
     * Prints error message.
     *
     * @param e Exception caught.
     */
    public String printError(Exception e) {
        return ("     " + e.getMessage());
    }

    /**
     * Prints when program terminates.
     */
    public String printExit() {
        return ("     Bye. Hope to see you again soon!");
    }
}
