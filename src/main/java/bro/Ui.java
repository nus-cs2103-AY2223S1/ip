package bro;

import java.util.ArrayList;
import java.util.Scanner;

import bro.task.Task;

/**
 * Ui class.
 */
public class Ui {

    private final Scanner Input = new Scanner(System.in);

    /**
     * Prints out the welcome message.
     */
    public static String welcome() {
        return "Hello! I'm THE BRO\n" + "What can I do for you?";
    }
    /**
     * Prints out the size of the ArrayList.
     * @param list1 The ArrayList
     */
    public static String listSize(ArrayList<Task> list1) {
        if (list1.size() == 1) {
            return "You have " + list1.size() + " task left!" + "\n";
        } else {
            return "You have " + list1.size() + " tasks left!" + "\n";
        }
    }

    /**
     * Prints the toString of the task.
     * @param t The task given by the user.
     */
    public static String printAdd(Task t) {
        return t.toString() + "\n";
    }

    /**
     * Prints out the mark statement.
     * @param list1 ArrayList of Task where the task has to be marked.
     * @param n Index of the task to be marked.
     */
    public static String markUi(ArrayList<Task> list1, int n) {
        return "I have marked this task\n" + (list1.get(n - 1)).toString() + "\n";
    }

    /**
     * Prints out the unmark statement.
     * @param list1 ArrayList of Task where the task has to be unmarked.
     * @param n Index of the task to be unmarked.
     */
    public static String unmarkUi(ArrayList<Task> list1, int n) {
        return "I have unmarked this task\n" + (list1.get(n - 1)).toString() + "\n";
    }

    /**
     * Prints out the delete statement.
     * @param list1 ArrayList of Task where the task has to be deleted.
     * @param n Index of the task to be deleted.
     */
    public static String deleteUi(ArrayList<Task> list1, int n) {
        return "I have removed this task.\n" + (list1.get(n - 1)).toString() + "\n";
    }

    /**
     * Prints out the bye statement.
     */
    public static String bye() {
        return "See you later broo!";
    }

    /**
     * Shows the error message for the file not being able to load.
     */
    public static String showLoadingError() {
        return "The file couldn't be loaded." + "\n";
    }

    /**
     * Prints out the message given.
     * @param msg The message to be printed out.
     */
    public static String errorMessage(String msg) {
        return msg + "\n";
    }
}
