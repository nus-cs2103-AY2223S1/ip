package common;

import tasklist.TaskList;
import tasks.Task;

import java.util.Scanner;

/**
 * Utility class that handles printing to terminal and reading user input.
 * Depreciated as it is now replaced with the ChatResponse class,
 * as now we no longer print Duke to the CLI.
 */
@Deprecated
public class Ui {

    /**
     * Prints the welcome text.
     */
    public static void showWelcome() {
        System.out.println("Hello! I'm Duke!\nWhat can I do for you?");
        printDivider();
    }

    /**
     * Prints a divider between commands.
     */
    public static void printDivider() {
        System.out.println("-----------------------------------");
    }

    /**
     * Prints a generic Exception.
     *
     * @param e Exception to print.
     */
    public static void printError(Exception e) {
        System.out.println(e.toString());
    }

    /**
     * Prints the prompt to obtain user command.
     */
    public static void printGetUserCommand() {
        System.out.println("Please enter a command: ");
    }

    /**
     * Reads the user input and returns a String representation.
     *
     * @return String representing user input.
     */
    public static String readUserCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Prints goodbye message.
     */
    public static void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Formats and prints the contents of a given TaskList object.
     *
     * @param taskList Task list to print.
     */
    public static void printTaskList(TaskList taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.format("%s. %s\n", i + 1, taskList.get(i));
        }
    }

    /**
     * Prints the flavour text when marking a task.
     *
     * @param task Task to mark.
     */
    public static void printMarkTask(Task task) {
        System.out.format("Nice! I've marked this task as done: %s\n", task);
    }

    /**
     * Prints the flavour text when marking a task.
     *
     * @param task Task to unmark.
     */
    public static void printUnmarkTask(Task task) {
        System.out.format("OK, I've marked this task as not done yet: %s\n", task);
    }

    /**
     * Prints the flavour text when adding a task.
     *
     * @param task     Task that is added.
     * @param taskList Current task list after task is added.
     */
    public static void printAddTask(Task task, TaskList taskList) {
        System.out.format("Got it. I've added this task:\n  %s\nNow you have %s %s in the list.\n", task, taskList.size(), taskList.size() != 1 ? "tasks" : "task");
    }

    /**
     * Prints the flavour text when deleting a task.
     *
     * @param task     Task that is added.
     * @param taskList Current task list after task is added.
     */
    public static void printDeleteTask(Task task, TaskList taskList) {
        System.out.format("Noted. I've removed this task:\n %s\nNow you have %s %s in the list.\n", task, taskList.size(), taskList.size() != 1 ? "tasks" : "task");
    }

    /**
     * Prints flavour text for creating new directory.
     *
     * @param dirName Name of directory.
     */
    public static void printCreateNewDirectory(String dirName) {
        System.out.printf("Creating new directory '%s' to store data...\n", dirName);
    }

    /**
     * Prints flavour text for creating new storage file.
     *
     * @param storageName Name of storage file.
     */
    public static void printCreateNewStorage(String storageName) {
        System.out.printf("Creating new file '%s' to store data...\n", storageName);
    }

    /**
     * Prints flavour text for saving task list to storage file.
     */
    public static void printSaving() {
        System.out.print("Saving...\n");
    }

    /**
     * Prints the task list where tasks matches a given target.
     */
    public static void printFindResults(TaskList taskList) {
        System.out.printf("Here are the matching tasks in your list: \n");
        printTaskList(taskList);
    }
}
