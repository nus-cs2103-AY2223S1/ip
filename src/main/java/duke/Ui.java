package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

/**
 * Represents the user interface for Duke.
 */
public class Ui {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String LINE = "```````````````````````````````````````````````````````````````````";
    private static final String GREET_WELCOME = "Hello there! I am\n" + LOGO
            + "\nyour personal task tracking assistant!\nWhat can I do for you today?\n";
    private static final String GREET_EXIT = "Bye. Hope to see you again soon!\n";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints a welcome message.
     */
    public static void showWelcome() {
        System.out.println(GREET_WELCOME + LINE);
    }

    /**
     * Prints a line.
     */
    public static void showLine() {
        System.out.println(LINE);
    }

    /**
     * Prints out error message.
     *
     * @param error error message
     */
    public static void showError(String error) {
        System.out.println(error);
    }

    /**
     * Prints list of stored <code>Task</code> from given <code>TaskList</code>.
     *
     * @param taskList list of stored tasks
     */
    public static void printTaskList(TaskList taskList) {
        String header = "";
        if (taskList.isEmpty()) {
            header = "You have no tasks in your list.";
        } else {
            header = "My List Of Tasks :D";
        }
        System.out.println(header + "\n" + taskList);
    }

    /**
     * Prints task creation message.
     *
     * @param newTask new task added to <code>TaskList</code>
     * @param noOfTasks number of tasks in this <code>TaskList</code>
     */
    public static void printTaskCreationMessage(Task newTask, int noOfTasks) {
        System.out.println("Got it. I've added this task:\n "
                + newTask + "\nNow you have " + noOfTasks + " tasks in the list.");
    }

    /**
     * Prints output for <code>Task</code> search.
     *
     * @param match string of <code>Task</code> matching the search keywords
     */
    public static void printTaskSearch(String match) {
        if (match == "") {
            System.out.println("Duke: Sorry! Cannot find any matching tasks in your list.");
        } else {
            System.out.println("Here are the matching tasks in your list:\n" + match);
        }
    }

    /**
     * Returns the next line of user input.
     *
     * @return next line of user input
     */
    public String readUserInput() {
        return scanner.nextLine();
    }

    /**
     * Closes scanner and prints exit message.
     */
    public void exit() {
        this.scanner.close();
        System.out.println(GREET_EXIT + LINE);
    }
}
