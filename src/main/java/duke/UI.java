package duke;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * An input/output handler that deals with interaction with the user.
 */
public class UI {
    private static final String LINE_BREAK =
            "____________________________________________________________";

    private Parser parser;

    public UI() {
        this.parser = new Parser();
    }

    /**
     * Starts the UI for the given Duke bot.
     *
     * @param duke The given Duke bot.
     */
    public void run(Duke duke) {
        Scanner scanner = new Scanner(System.in);
        print("Hello! I'm Duke\nWhat can I do for you?");
        String s;
        while (true) {
            s = scanner.nextLine();
            if (parser.parse(s)) {
                parser.runCommand(duke);
            } else {
                if (s.equals("bye")) {
                    print("Bye. Hope to see you again soon!");
                    return;
                } else {
                    printError("Please enter a valid command:\n\n" + "mark\n"
                            + "unmark\n" + "list\n" + "todo\n" + "deadline\n" + "event");
                }
            }
        }
    }

    /**
     * Prints the given string.
     *
     * @param s The given string.
     */
    public void print(String s) {
        System.out.println(LINE_BREAK);
        System.out.println(s);
        System.out.println(LINE_BREAK);
        System.out.println();
    }

    /**
     * Prints the given error message.
     *
     * @param errorMessage The error message.
     */
    public void printError(String errorMessage) {
        System.out.println(LINE_BREAK);
        System.out.println("----Error----\n" + errorMessage);
        System.out.println(LINE_BREAK);
        System.out.println();
    }

    /**
     * Prints a header message followed by a numbered list of tasks in the list.
     *
     * @param tasks The task list.
     * @param headerMessage The header message.
     */
    public void printTasks(ArrayList<Task> tasks, String headerMessage) {
        System.out.println(LINE_BREAK);
        System.out.println(headerMessage);
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println(LINE_BREAK);
        System.out.println();
    }

    /**
     * Prints the task that was added and the total number of tasks in the list.
     *
     * @param t The task added.
     * @param count The number of tasks in the list.
     */
    public void printTaskAdded(Task t, int count) {
        System.out.println(LINE_BREAK);
        System.out.println("Got it. I've added this task:\n"
                + "  " + t);
        System.out.println("Now you have " + count + " tasks in the list.");
        System.out.println(LINE_BREAK);
        System.out.println();
    }

    /**
     * Prints the task that was deleted and the total number of tasks in the list.
     *
     * @param t The task deleted.
     * @param count The number of tasks in the list.
     */
    public void printTaskDeleted(Task t, int count) {
        System.out.println(LINE_BREAK);
        System.out.println("Noted. I've removed this task:\n"
                + "  " + t);
        System.out.println("Now you have " + count + " tasks in the list.");
        System.out.println(LINE_BREAK);
        System.out.println();
    }

    /**
     * Prints the task that was marked or unmarked.
     *
     * @param t The task.
     * @param b Whether the task was marked.
     */
    public void printTaskMarked(Task t, boolean b) {
        if (b) {
            print("Nice! I've marked this task as done: \n"
                    + "  " + t);
        } else {
            print("OK, I've marked this task as not done: \n"
                    + "  " + t);
        }
    }
}
