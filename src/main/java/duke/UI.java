package duke;

import java.util.Scanner;

import duke.task.Task;

/**
 * Represents the UI of Duke.
 */
public class UI {
    public static final String DIVIDER = "____________________________________________________________";
    private final Scanner sc;

    public UI() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints given message with indentation.
     * @param str Message to be printed.
     */
    public void print(String str) {
        System.out.println("\t" + str);
    }

    /**
     * Prints out TaskList in a form of a list.
     * @param ls TaskList to be printed.
     */
    public void print(TaskList ls) {
        print(DIVIDER);
        print("Here are the tasks in your list:");
        for (int i = 0; i < ls.getSize(); i++) {
            print((i + 1) + ". " + ls.taskToString(i));
        }
        print(DIVIDER);
    }

    /**
     * Prints message with bottom and top divider with indentation.
     * @param message Message to be printed.
     */
    public void printWithDivider(String message) {
        System.out.println("\t" + DIVIDER);
        System.out.println("\t" + message);
        System.out.println("\t" + DIVIDER);
    }

    /**
     * Prints add message whenever a new Task has been added with indentation.
     * @param toAdd Task to be added.
     * @param size Size of TaskList.
     */
    public void printAdd(Task toAdd, int size) {
        String mess = "Got it. I've added this task:\n\t\t" + toAdd
                + "\n\tNow you have " + size + " tasks in the list.";
        printWithDivider(mess);
    }

    /**
     * Prints out TaskList in form of a list and the given title.
     * @param ls TaskList to be printed.
     * @param title Title of printed TaskList.
     */
    public void printTaskList(TaskList ls, String title) {
        print(DIVIDER);
        print(title);
        for (int i = 0; i < ls.getSize(); i++) {
            print((i + 1) + ". " + ls.taskToString(i));
        }
        print(DIVIDER);
    }

    public String readInput() {
        return sc.nextLine();
    }

    /**
     * Welcomes user.
     */
    public void greet() {
        printWithDivider("Hello! I'm Lily\n"
                + "\t" + "| |     ^^^  | |   |^|    |^|\n"
                + "\t" + "| |     | |  | |   \\ \\   / /\n"
                + "\t" + "| |     | |  | |    \\ \\ / /\n"
                + "\t" + "| |___  | |  | |___   | |\n"
                + "\t" + "|_____/ | |  |_____/  |_|\n"
                + "\t" + "What can I do for you?");
    }

    /**
     * Says bye and closes scanner.
     */
    public void exit() {
        printWithDivider("Bye. Hope to see you again soon!");
        sc.close();
    }

}
