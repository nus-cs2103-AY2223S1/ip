import TaskItems.TaskItem;

import java.util.ArrayList;

/**
 * DukePrinter to print stuff to the console.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */

public class DukePrinter {
    /**
     * Prints a horizontal line.
     */
    private static void print_line() {
        System.out.print("\t____________________________________________________________\n");
    }

    /**
     * Prints a message.
     * @param message a message.
     */
    private static void print(String message) {
        System.out.print("\t" + message + "\n");
    }

    /**
     * Prints a greeting message.
     */
    public static void greet() {
        print_line();
        print("Hello! I'm Duke");
        print("What can I do for you?");
        print_line();
    }

    /**
     * Prints a exit message.
     */
    public static void exit() {
        print_line();
        print("Bye. Hope to see you again soon!");
        print_line();
    }

    /**
     * Prints a message.
     * @param message a message.
     */
    public static void echo(String message) {
        print_line();
        print(message);
        print_line();
    }

    /**
     * Prints list of TaskItems.
     * @param taskItems list of TaskItems.
     */
    public static void list(ArrayList<TaskItem> taskItems) {
        print_line();
        int i = 1;
        for (TaskItem taskItem : taskItems) {
            print(i++ + "." + taskItem.toString());
        }
        print_line();
    }

    /**
     * Prints a mark message.
     * @param taskItem string representation of a TaskItem.
     */
    public static void mark(String taskItem) {
        print_line();
        print("Nice! I've marked this task as done:");
        print("  " + taskItem);
        print_line();
    }

    /**
     * Prints unmark message.
     * @param taskItem string representation of a TaskItem.
     */
    public static void unmark(String taskItem) {
        print_line();
        print("OK, I've marked this task as not done yet:");
        print("  " + taskItem);
        print_line();
    }

    /**
     * Prints an add message.
     * @param taskItem string representation of a TaskItem.
     * @param size size of the list.
     */
    public static void add(String taskItem, int size) {
        print_line();
        print("Got it. I've added this task:");
        print("  " + taskItem);
        print("Now you have " + size + " tasks in the list.");
        print_line();
    }
}
