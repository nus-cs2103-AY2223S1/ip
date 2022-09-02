package bro;

import bro.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private final Scanner INPUT = new Scanner(System.in);

    /**
     * Prints out the welcome message.
     */
    public void welcome(){
        System.out.println("Hello! I'm THE BRO\n" + "What can I do for you?");
    }

    /**
     * Gets the inout from the user.
     * @return The next line given by the user.
     */
    public String readCommand() {
        return INPUT.nextLine();
    }

    /**
     * Prints out the size of the ArrayList.
     * @param list1 The ArrayList
     */
    public static void listSize(ArrayList<Task> list1){
        System.out.println("You have " + list1.size() + " tasks left!");
    }

    /**
     * Prints the toString of the task.
     * @param t The task given by the user.
     */
    public static void printAdd(Task t){
        System.out.println(t.toString());
    }

    /**
     * Prints out the mark statement.
     * @param list1 ArrayList of Task where the task has to be marked.
     * @param n Index of the task to be marked.
     */
    public static void markUi(ArrayList<Task> list1, int n){
        System.out.println("I have marked this task\n" + (list1.get(n-1)).toString());
    }

    /**
     * Prints out the unmark statement.
     * @param list1 ArrayList of Task where the task has to be unmarked.
     * @param n Index of the task to be unmarked.
     */
    public static void unmarkUi(ArrayList<Task> list1, int n){
        System.out.println("I have unmarked this task\n" + (list1.get(n-1)).toString());
    }

    /**
     * Prints out the delete statement.
     * @param list1 ArrayList of Task where the task has to be deleted.
     * @param n Index of the task to be deleted.
     */
    public static void deleteUi(ArrayList<Task> list1, int n){
        System.out.println("I have removed this task.\n" + (list1.get(n-1)).toString());
    }

    /**
     * Prints out the bye statement.
     */
    public static void bye(){
        System.out.println("See you later broo!");
    }

    /**
     * Shows the error message for the file not being able to load.
     */
    public static void showLoadingError(){
        System.out.println("The file couldn't be loaded.");
    }

    /**
     * Prints out the message given.
     * @param msg The message to be printed out.
     */
    public static void errorMessage(String msg){
        System.out.println(msg);
    }

}
