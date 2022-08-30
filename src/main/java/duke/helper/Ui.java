package duke.helper;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Child containing default messages
 */
public class Ui {
    /**
     * Method to print welcome message
     */
    public static void welcome() {
        System.out.println("-------------------------------------------\n"
                + "Hello from Phil\n"
                        + "How may I assist you on this fine day?\n" +
                                "-------------------------------------------\n");
    }

    /**
     * Method to print bye message
     */
    public static String bye() {
        return "See you later alligator!"
                + "-------------------------------------------";
    }

    /**
     * Method to print add task message
     *
     * @param task the task to be added
     */
    public static String add(Task task) {
        return "added: "
                + task.toString();
    }

    /**
     * Method to print mark task message
     */
    public static String mark() {
        return "Roger sir the task has been marked!";
    }

    /**
     * Method to print unmark task message
     */
    public static String unmark() {
        return "Aww okay the task has been unmarked.";
    }

    /**
     * Method to print delete task message
     *
     * @param task the task to be deleted
     */
    public static String delete(Task task) {
        return "Alrighty! I have deleted the following task:\n"
                + task.toString();
    }

    /**
     * Method to print count task message
     *
     * @param list the list to be counted
     */
    public static String countTasks(TaskList list) {
        return "We now have "
                + list.getSize() + " tasks left.";
    }

    /**
     * Method to print clear list message
     */
    public static String clear() {
        return "The list has been successfully cleared!";
    }

    /**
     * Method to print task found message
     */
    public static String taskFound() {
        return "Woohoo here are some matches found!";
    }

    /**
     * Method to print no task found message
     */
    public static String noTaskFound() {
        return "Ohno I could not find any tasks fitting the keywords...";
    }

    /**
     * Method to print line break after a command
     */
    public static String line() {
        return "-------------------------------------------";
    }
}
