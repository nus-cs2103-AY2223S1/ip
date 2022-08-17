import java.lang.StringBuilder;
import java.util.Scanner;

/**
 * The Duke class is a personal chatbot assistant.
 */
public class Duke {
    private static final String INDENTATION = "    ";
    private static final String LINE = INDENTATION
            + "____________________________________________________________";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    /**
     * Prints an indented horizontal line.
     */
    private static void printLine() {
        System.out.println(LINE);
    }

    /**
     * Adds a task to be tracked and
     * prints a message that the task is added.
     *
     * @param task The specified task to be added onto the tracked list.
     */
    private static void addTask(Task task) {
        tasks[taskCount++] = task;
        printLine();
        System.out.println(INDENTATION + "Got it. I've added this task:\n"
                + INDENTATION + "  " + task);
        if (taskCount == 1) {
            System.out.println(INDENTATION + "Now you have 1 task in your list.");
        } else {
            System.out.println(INDENTATION + "Now you have " + taskCount + " tasks in your list.");
        }
        printLine();
    }

    /**
     * Checks if a given string value represents an integer.
     *
     * @param s The specified string value to check.
     * @return true if the string represents an integer.
     */
    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * The main method for the chatbot.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        System.out.println(INDENTATION + "Hello from\n" + LOGO);
        printLine();
        System.out.println(INDENTATION + "Hello! I'm Duke\n"
                + INDENTATION + "What can I do for you?");
        printLine();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                printLine();
                System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
                break;

            } else if (input.equals("list")) {
                printLine();
                System.out.println(INDENTATION + "Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(INDENTATION + (i + 1) + "." + tasks[i]);
                }
                printLine();

            } else {
                String[] strings = input.split(" ");

                if (strings[0].equals("mark") && strings.length == 2 && isInteger(strings[1])) {
                    Task specifiedTask = tasks[Integer.parseInt(strings[1]) - 1];
                    specifiedTask.markAsDone();
                    printLine();
                    System.out.println(INDENTATION + "Nice! I've marked this task as done:\n"
                            + INDENTATION + "  " + specifiedTask);
                    printLine();

                } else if (strings[0].equals("unmark") && strings.length == 2 && isInteger(strings[1])) {
                    Task specifiedTask = tasks[Integer.parseInt(strings[1]) - 1];
                    specifiedTask.unmarkAsDone();
                    printLine();
                    System.out.println(INDENTATION + "OK, I've marked this task as not done yet:\n"
                            + INDENTATION + "  " + specifiedTask);
                    printLine();

                } else if (strings[0].equals("todo") && strings.length > 1) {
                    addTask(new Todo(input.substring(5)));

                } else if (strings[0].equals("deadline")  && strings.length > 1) {
                    input = input.substring(9);
                    strings = input.split(" /by ");
                    addTask(new Deadline(strings[0], strings[1]));

                } else if (strings[0].equals("event") && strings.length > 1) {
                    input = input.substring(6);
                    strings = input.split(" /at ");
                    addTask(new Event(strings[0], strings[1]));

                } else {
                    printLine();
                    System.out.println(INDENTATION + input);
                    printLine();
                }
            }
        }

        sc.close();
    }
}
