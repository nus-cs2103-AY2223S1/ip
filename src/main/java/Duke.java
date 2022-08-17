import java.util.Scanner;

public class Duke {
    private static final String INDENTATION = "    ";
    private static final String LINE = INDENTATION + "──────────────────────────────────────────────────";
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
     * Checks if a given string value represents an integer.
     *
     * @param s The specified string value to check.
     * @return true if the string represents is an integer.
     */
    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

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
                    System.out.println(INDENTATION + (i + 1) + ". " + tasks[i]);
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

                } else {
                    tasks[taskCount++] = new Task(input);
                    printLine();
                    System.out.println(INDENTATION + "added: " + input);
                    printLine();
                }
            }
        }

        sc.close();
    }
}
