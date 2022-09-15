package duke;

import duke.exceptions.DukeArrayOutOfBoundException;
import duke.exceptions.DukeMissingIndexException;

import java.util.Scanner;

/**
 * Represents an <code>Ui</code> class that handle user interaction.
 */
public class Ui {

    private static final String EXIT = "See you later :)";
    private static final Scanner MYSCANNER = new Scanner(System.in);

    /**
     * Prints exit message.
     */
    public static String printExit() {
        return EXIT;
    }

    /**
     * Prints read message.
     */
    public static String printRead() {
        return TaskList.read();
    }

    /**
     * Prints successful loading message.
     */
    public static void printSuccessfulLoad() {
        System.out.println("Successfully retrieved most recent TaskList");
    }

    /**
     * Prints error when loading failed.
     */
    public static void printFailedLoad() {
        System.out.println("Error loading file, an empty TaskList is initialised");
    }

    /**
     * Prints task marked message.
     */
    public static String printTaskIsDone(int index) {
        if (index >= TaskList.getTaskList().size()) {
            return "Error";
        }
        Task task = TaskList.getTaskList().get(index);
        return "Task is marked as Done \n" + task;
    }

    /**
     * Prints task unmarked message.
     */
    public static String printTaskIsUndone(int index) {
        if (index >= TaskList.getTaskList().size()) {
            return "Error";
        }
        Task task = TaskList.getTaskList().get(index);
        return "Task is marked as Undone \n" + task;
    }

    /**
     * Prints <code>Task</code> added to <code>TaskList</code> message.
     *
     * @param task <code>Task</code> to be added.
     */
    public static String printAddTask(Task task) {
        return "Added the task \n" + task;
    }

    /**
     * Prints <code>Task</code> deleted from <code>TaskList</code> message.
     *
     * @param index index of task to be deleted.
     */
    public static String printDeleteTask(int index) {

        try {
            if (index >= TaskList.getTaskList().size()) {
                throw new DukeMissingIndexException();
            }
        } catch (DukeMissingIndexException e) {
            return printError(e.getMessage());
        }

        Task task = TaskList.getTaskList().get(index);
        try {
            TaskList.delete(index);
        } catch (DukeMissingIndexException e) {
            return printError(e.getMessage());
        }
        return "Removed the task \n" + task;
    }

    /**
     * Prints error messages.
     *
     * @param error error message to be printed
     */
    public static String printError(String error) {
        return error;
    }

    /**
     * Prints the found tasks.
     *
     * @param prefix prefix of tasks to find.
     */
    public static String printFind(String prefix) {
        return TaskList.find(prefix);
    }

    /**
     * Returns the command inputted by user
     *
     * @return String representing input by user
     */
    public static String getCommand() {
        return MYSCANNER.nextLine().trim();
    }


}
