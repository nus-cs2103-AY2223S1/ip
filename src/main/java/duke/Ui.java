package duke;

import java.util.Scanner;

/**
 * Represents an <code>Ui</code> class that handle user interaction.
 */
public class Ui {
    private static final String WELCOME = "Hello! I am Duke\n "
            + "What can i do for you";
    private static final String EXIT = "See you later :)";
    private static final String SEPARATOR = "------------------------------------";
    private static final String ASK_FOR_COMMAND = "What do you want me to do?";
    private static final Scanner myScanner = new Scanner(System.in);

    /**
     * Prints separator.
     */
    public static void printSeparator() {
        System.out.println(SEPARATOR);
    }

    /**
     * Prints welcome message.
     */
    public static void printWelcome() {
        printSeparator();
        System.out.println(WELCOME);
        printSeparator();
    }

    /**
     * Prints exit message.
     */
    public static void printExit() {
        printSeparator();
        System.out.println(EXIT);
        printSeparator();
    }

    /**
     * Prints read message.
     */
    public static void printRead() {
        printSeparator();
        TaskList.read();
        printSeparator();
    }

    /**
     * Prints successful loading message.
     */
    public static void printSuccessfulLoad() {
        printSeparator();
        System.out.println("Successfully retrieved most recent TaskList");
        printSeparator();
    }

    /**
     * Prints error when loading failed.
     */
    public static void printFailedLoad() {
        printSeparator();
        System.out.println("Error loading file, an empty TaskList is initialised");
        printSeparator();
    }

    /**
     * Prints message asking user to input command.
     */
    public static void printAskForCommand() {
        printSeparator();
        System.out.println(ASK_FOR_COMMAND);
    }

    /**
     * Prints task marked message.
     */
    public static void printTaskIsDone(int index) {
        if (index >= TaskList.getTaskList().size()) {
            return;
        }
        Task task = TaskList.getTaskList().get(index);
        printSeparator();
        System.out.println("Task is marked as Done \n" + task.toString());
        printSeparator();
    }

    /**
     * Prints task unmarked message.
     */
    public static void printTaskIsUndone(int index) {
        if (index >= TaskList.getTaskList().size()) {
            return;
        }
        Task task = TaskList.getTaskList().get(index);
        printSeparator();
        System.out.println("Task is marked as Undone \n" + task.toString());
        printSeparator();
    }

    /**
     * Prints <code>Task</code> added to <code>TaskList</code> message.
     *
     * @param task <code>Task</code> to be added.
     */
    public static void printAddTask(Task task) {

        printSeparator();
        System.out.println("added: " + task.toString());
        printSeparator();
    }

    /**
     * Prints <code>Task</code> deleted from <code>TaskList</code> message.
     *
     * @param index index of task to be deleted.
     */
    public static void printDeleteTask(int index) {

        if (index >= TaskList.getTaskList().size()) {
            return;
        }
        Task task = TaskList.getTaskList().get(index);
        printSeparator();
        System.out.println("Removed the task \n" + task.toString());
        printSeparator();
    }

    /**
     * Prints error messages.
     *
     * @param error error message to be printed
     */
    public static void printError(String error) {
        System.out.println(error);
    }

    /**
     * Prints the found tasks.
     *
     * @param prefix prefix of tasks to find.
     */
    public static void printFind(String prefix) {
        printSeparator();
        TaskList.find(prefix);
        printSeparator();
    }

    /**
     * Returns the command inputted by user
     *
     * @return String representing input by user
     */
    public static String getCommand() {
        return myScanner.nextLine().trim();
    }



}
