package duke;

import duke.task.Task;
import java.util.Scanner;

public class UI {
    public static String DIVIDER = "____________________________________________________________";
    private final Scanner sc;

    public UI() {
        this.sc = new Scanner(System.in);
    }

    public void print(String str) {
        System.out.println("\t" + str);
    }

    public void printWithDivider(String message) {
        System.out.println("\t" + DIVIDER);
        System.out.println("\t" + message);
        System.out.println("\t" + DIVIDER);
    }

    public void printAdd(Task toAdd, int size) {
        String mess = "Got it. I've added this task:\n\t\t" + toAdd +
                "\n\tNow you have " + size + " tasks in the list.";
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
        for (int i = 0; i < ls.getSize() ; i++) {
            print((i + 1) + ". " + ls.taskToString(i));
        }
        print(DIVIDER);
    }

    public String readInput() {
        return sc.nextLine();
    }

    public void greet() {
        printWithDivider("Hello! I'm Lily\n"
                + "\t" + "| |     ^^^  | |   |^|    |^|\n"
                + "\t" + "| |     | |  | |   \\ \\   / /\n"
                + "\t" + "| |     | |  | |    \\ \\ / /\n"
                + "\t" + "| |___  | |  | |___   | |\n"
                + "\t" + "|_____/ | |  |_____/  |_|\n"
                + "\t" + "What can I do for you?");
    }

    public void exit() {
        printWithDivider("Bye. Hope to see you again soon!");
        sc.close();
    }

}
