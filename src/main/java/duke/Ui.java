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
    private static final String GREET_WELCOME = "Hello ! I am Duke, your task tracking assistant!";
    private static final String GREET_EXIT = "Bye. Hope to see you again soon!";

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
            header = "My List Of Tasks :>";
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
        return String.format("Noted. I've removed this task:\n %s\nNow you have %d tasks in the list",
                deletedTask, noOfTasks);
    }

    /**
     * Prints output for <code>Task</code> search.
     *
     * @param match string of <code>Task</code> matching the search keywords
     */
    public static String printTaskSearch(String match) {
        String output;
        if (match.isBlank()) {
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
