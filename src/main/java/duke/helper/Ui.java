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
        System.out.println("-------------------------------------------");
        System.out.println("Hello from Phil");
        System.out.println("How may I assist you on this fine day?");
        System.out.println("-------------------------------------------");
    }

    /**
     * Method to print bye message
     */
    public static void bye() {
        System.out.println("See you later alligator!");
        System.out.println("-------------------------------------------");
    }

    /**
     * Method to print add task message
     *
     * @param task the task to be added
     */
    public static void add(Task task) {
        System.out.println("added: "
                + task.toString());
    }

    /**
     * Method to print mark task message
     */
    public static void mark() {
        System.out.println("Roger sir the task has been marked!");
    }

    /**
     * Method to print unmark task message
     */
    public static void unmark() {
        System.out.println("Aww okay the task has been unmarked.");
    }

    /**
     * Method to print delete task message
     *
     * @param task the task to be deleted
     */
    public static void delete(Task task) {
        System.out.println("Alrighty! I have deleted the following task:");
        System.out.println(task.toString());
    }

    /**
     * Method to print count task message
     *
     * @param list the list to be counted
     */
    public static void countTasks(TaskList list) {
        System.out.println("We now have "
                + list.getSize() + " tasks left.");
    }

    /**
     * Method to print clear list message
     */
    public static void clear() {
        System.out.println("The list has been successfully cleared!");
    }

    /**
     * Method to print task found message
     */
    public static void taskFound() {
        System.out.println("Woohoo here are some matches found!");
    }

    /**
     * Method to print no task found message
     */
    public static void noTaskFound() {
        System.out.println("Ohno I could not find any tasks fitting the keywords...");
    }

    /**
     * Method to print line break after a command
     */
    public static void line() {
        System.out.println("-------------------------------------------");
    }
}
