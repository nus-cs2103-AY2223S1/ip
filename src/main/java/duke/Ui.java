package duke;

import duke.task.Note;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the user interface for Duke.
 */
public abstract class Ui {
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
        if (taskList.isEmpty()) {
            return "OOPS! Nothing to see here";
        } else {
            return taskList.toString();
        }
    }

    /**
     * Prints task creation message.
     *
     * @param newTask new task added to <code>TaskList</code>
     * @param noOfTasks number of tasks in this <code>TaskList</code>
     * @return successful task creation message
     */
    public static String printTaskCreationMessage(Task newTask, int noOfTasks) {
        String type = newTask instanceof Note ? "note" : "task";
        return String.format("Got it. I've added %s :\n %s\nNow you have %d %ss in the list",
                type, newTask, noOfTasks, type);
    }

    /**
     * @param deletedTask task deleted from <code>TaskList</code>
     * @param noOfTasks number of tasks in this <code>TaskList</code>
     * @return successful task deletion message
     */
    public static String printTaskDeletionMessage(Task deletedTask, int noOfTasks) {
        String type = deletedTask instanceof Note ? "note" : "task";
        return String.format("Noted. I've removed :\n %s\nNow you have %d %ss in the list",
                deletedTask, noOfTasks, type);
    }

    /**
     * Prints output for <code>Task</code> search.
     *
     * @param match string of <code>Task</code> matching the search keywords
     */
    public static String printTaskSearch(String match) {
        if (match.isBlank()) {
            return "Duke: Sorry! Cannot find any matching tasks in your list.";
        } else {
            return "Here are the matching tasks in your list:\n" + match;
        }
    }

    /**
     * Prints exit message.
     */
    public static String printExit() {
        return GREET_EXIT;
    }
}
