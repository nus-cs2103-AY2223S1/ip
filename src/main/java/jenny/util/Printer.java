package jenny.util;

import jenny.tasks.AbstractTask;

import java.util.ArrayList;

/**
 * Prints stuff to the console.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */

public final class Printer {
    /**
     * Prints a horizontal line.
     */
    private static void print_line() {
        System.out.print("\t____________________________________________________________\n");
    }

    /**
     * Prints a message.
     *
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
        print("Hello! I'm JennyBot");
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
     *
     * @param message a message.
     */
    public static void echo(String message) {
        print_line();
        print(message);
        print_line();
    }

    /**
     * Prints a message.
     *
     * @param scope   location of the message.
     * @param message a message.
     */
    public static void echo(String scope, String message) {
        print_line();
        print(String.format("%s: %s", scope, message));
        print_line();
    }

    /**
     * Prints list of tasks.
     *
     * @param tasks list of tasks.
     */
    public static void list(ArrayList<AbstractTask> tasks) {
        print_line();
        int i = 1;
        for (AbstractTask task : tasks) {
            print(i++ + "." + task.toString());
        }
        print_line();
    }

    /**
     * Prints a mark message.
     *
     * @param task string representation of a task.
     */
    public static void mark(String task) {
        print_line();
        print("Nice! I've marked this task as done:");
        print("  " + task);
        print_line();
    }

    /**
     * Prints unmark message.
     *
     * @param task string representation of a task.
     */
    public static void unmark(String task) {
        print_line();
        print("OK, I've marked this task as not done yet:");
        print("  " + task);
        print_line();
    }

    /**
     * Prints an add message.
     *
     * @param task string representation of a task.
     * @param size     size of the list.
     */
    public static void add(String task, int size) {
        print_line();
        print("Got it. I've added this task:");
        print("  " + task);
        print("Now you have " + size + " tasks in the list.");
        print_line();
    }

    /**
     * Prints a delete message.
     *
     * @param task string representation of a task.
     * @param size     size of the list.
     */
    public static void delete(String task, int size) {
        print_line();
        print("Got it. I've added this task:");
        print("  " + task);
        print("Now you have " + size + " tasks in the list.");
        print_line();
    }
}
