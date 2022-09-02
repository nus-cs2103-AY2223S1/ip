package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

/**
 * Represents the user interface for Duke.
 */
public abstract class Ui {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREET_WELCOME = "Hello there! I am\n" + LOGO + "your personal task tracking assistant!\nWhat can I do for you today?\n";
    private static final String GREET_EXIT = "Bye. Hope to see you again soon!\n";

    /**
     * Prints a welcome message.
     */
    public static String printWelcome() {
        return GREET_WELCOME;
    }

    /**
     * Prints list of stored <code>Task</code> from given <code>TaskList</code>.
     *
     * @param taskList list of stored tasks
     */
    public static String printTaskList(TaskList taskList) {
        String header;
        if (taskList.isEmpty()) {
            header = "You have no tasks in your list.";
        } else {
            header = "My List Of Tasks :D";
        }
        return header + "\n" + taskList;
    }

    /**
     * Prints task creation message.
     *
     * @param newTask new task added to <code>TaskList</code>
     * @param noOfTasks number of tasks in this <code>TaskList</code>
     * @return successful task creation message
     */
    public static String printTaskCreationMessage(Task newTask, int noOfTasks) {
        String createdTaskMessage = "Got it. I've added this task:\n "
                + newTask + "\nNow you have " + noOfTasks + " tasks in the list.";
        return createdTaskMessage;
    }

    /**
     * @param deletedTask task deleted from <code>TaskList</code>
     * @param noOfTasks number of tasks in this <code>TaskList</code>
     * @return successful task deletion message
     */
    public static String printTaskDeletionMessage(Task deletedTask, int noOfTasks) {
        String deletedTaskMessage = "Noted. I've removed this task:\n "+ deletedTask
                + "\nNow you have " + noOfTasks + " tasks in the list";
        return deletedTaskMessage;
    }

    /**
     * Prints output for <code>Task</code> search.
     *
     * @param match string of <code>Task</code> matching the search keywords
     */
    public static String printTaskSearch(String match) {
        String output;
        if (match == "") {
            output = "Duke: Sorry! Cannot find any matching tasks in your list.";
        } else {
            output = "Here are the matching tasks in your list:\n" + match;
        }
        return output;
    }

    /**
     * Prints exit message.
     */
    public static String printExit() {
        return GREET_EXIT;
    }
}
