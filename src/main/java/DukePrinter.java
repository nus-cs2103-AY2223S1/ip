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
     * Prints horizontal line.
     */
    private static void print_line() {
        System.out.print("\t____________________________________________________________\n");
    }

    /**
     * Prints String.
     * @param printable string.
     */
    public static void print(String printable) {
        System.out.print("\t" + printable + "\n");
    }

    /**
     * Prints greeting.
     */
    public static void greet() {
        print_line();
        print("Hello! I'm Duke");
        print("What can I do for you?");
        print_line();
    }

    /**
     * Prints exit message.
     */
    public static void exit() {
        print_line();
        print("Bye. Hope to see you again soon!");
        print_line();
    }

    /**
     * Prints add message.
     * @param s string representation of TaskItem.
     */
    public static void echo(String s) {
        print_line();
        print(s);
        print_line();
    }

    /**
     * Prints addError message.
     * @param s string representation of TaskItem.
     */
    public static void addError(String s) {
        print_line();
        print("Error adding " + s);
        print_line();
    }

    /**
     * Prints list of TaskItems.
     * @param taskItems list of TaskItems to be printed.
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
     * Prints mark message.
     * @param s string representation of TaskItem.
     */
    public static void mark(String s) {
        print_line();
        print("Nice! I've marked this task as done:");
        print("  " + s);
        print_line();
    }

    /**
     * Prints markError message.
     * @param s string representation of TaskItem.
     */
    public static void markError(String s) {
        print_line();
        print("Error marking " + s);
        print_line();
    }

    /**
     * Prints unmark message.
     * @param s string representation of TaskItem.
     */
    public static void unmark(String s) {
        print_line();
        print("OK, I've marked this task as not done yet:");
        print("  " + s);
        print_line();
    }

    /**
     * Prints unmarkError message.
     * @param s string representation of TaskItem.
     */
    public static void unmarkError(String s) {
        print_line();
        print("Error unmarking " + s);
        print_line();
    }

    public static void add(String s, int i) {
        print_line();
        print("Got it. I've added this task:");
        print("  " + s);
        print("Now you have " + i + " tasks in the list.");
        print_line();
    }
}
