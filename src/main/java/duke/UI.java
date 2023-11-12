package duke;

import java.util.Scanner;

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
        System.out.println(str);
    }

    /**
     * Prints message with bottom and top divider with indentation.
     * @param message Message to be printed.
     */
    public void printWithDivider(String message) {
        System.out.println(DIVIDER);
        System.out.println(message);
        System.out.println(DIVIDER);
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
    /**
     * Returns string contains TaskList in form of a list and the given title.
     * @param ls TaskList to be printed.
     * @param title Title of printed TaskList.
     */
    public String getTaskListResponse(TaskList ls, String title) {
        if (ls == null || ls.getSize() == 0) {
            return "You don't have any task.";
        }
        String mess = title + "\n";
        for (int i = 0; i < ls.getSize(); i++) {
            mess += (i + 1) + ". " + ls.taskToString(i) + "\n";
        }
        return mess;
    }

    public String readInput() {
        return sc.nextLine();
    }

    /**
     * Welcomes user.
     */
    public void greet() {
        printWithDivider("Hello! I'm Lily\n"
                 + "| |     ^^^  | |   |^|    |^|\n"
                 + "| |     | |  | |   \\ \\   / /\n"
                 + "| |     | |  | |    \\ \\ / /\n"
                 + "| |___  | |  | |___   | |\n"
                 + "|_____/ | |  |_____/  |_|\n"
                 + "What can I do for you?");
    }

    /**
     * Says bye and closes scanner.
     */
    public void exit() {
        printWithDivider("Bye. Hope to see you again soon!");
        sc.close();
    }
    /**
     * Returns exit message.
     */
    public String getExitMessage() {
        return "Bye. Hope to see you again soon!";
    }

}
