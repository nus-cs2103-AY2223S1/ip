package duke.helper;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Encapsulates a child containing default messages
 */
public class Ui {
    /**
     * Returns welcome message
     */
    public static String welcome() {
        return "\nHello from Phil\n"
                + "How may I assist you on this fine day?\n"
                        + "type 'help' to get a lists of all the commands!\n";
    }

    /**
     * Returns bye message
     */
    public static String bye() {
        return "I shall take my leave in 3 seconds! See you later alligator!";
    }

    /**
     * Returns add task message
     *
     * @param task the task to be added
     */
    public static String add(Task task) {
        return "added: "
                + task.toString();
    }

    /**
     * Returns mark task message
     */
    public static String mark() {
        return "Roger sir the task has been marked!";
    }

    /**
     * Returns unmark task message
     */
    public static String unmark() {
        return "Aww okay the task has been unmarked.";
    }

    /**
     * Returns delete task message
     *
     * @param task the task to be deleted
     */
    public static String delete(Task task) {
        return "Alrighty! I have deleted the following task:\n"
                + task.toString();
    }

    /**
     * Returns count task message
     *
     * @param list the list to be counted
     */
    public static String countTasks(TaskList list) {
        return "We now have "
                + list.getSize() + " tasks left.";
    }

    /**
     * Returns clear list message
     */
    public static String clear() {
        return "The list has been successfully cleared!";
    }

    /**
     * Returns task found message
     */
    public static String taskFound() {
        return "Woohoo here are some matches found!";
    }

    /**
     * Returns no task found message
     */
    public static String noTaskFound() {
        return "Ohno I could not find any tasks fitting the keywords...";
    }

    /**
     * Returns invalid command message
     */
    public static String invalidCommand() {
        return "Invalid command, please try again.";
    }
}
