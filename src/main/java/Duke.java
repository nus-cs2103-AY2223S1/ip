import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Duke class is a personal chatbot assistant.
 */
public class Duke {
    private static final String INDENTATION = "    ";
    private static final String LINE = INDENTATION
            + "____________________________________________________________";
    private static final String LOGO = INDENTATION + " ____        _        \n"
            + INDENTATION + "|  _ \\ _   _| | _____ \n"
            + INDENTATION + "| | | | | | | |/ / _ \\\n"
            + INDENTATION + "| |_| | |_| |   <  __/\n"
            + INDENTATION + "|____/ \\__,_|_|\\_\\___|";

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int taskCount = 0;
    private static boolean isBye;

    private enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT;
    }

    /**
     * Prints an indented horizontal line.
     */
    private static void printLine() {
        System.out.println(LINE);
    }

    /**
     * Prints an error message from Duke.
     *
     * @param message The message that describes the error with Duke.
     */
    private static void printError(String message) {
        printLine();
        System.out.println(INDENTATION + "☹ OOPS!!! " + message);
        printLine();
    }

    /**
     * Prints the first message from Duke when it starts running.
     */
    private static void printStart() {
        System.out.println(INDENTATION + "Hello from\n" + LOGO);
        printLine();
        System.out.println(INDENTATION + "Hello! I'm Duke\n"
                + INDENTATION + "What can I do for you?");
        printLine();
    }

    /**
     * Exits from Duke.
     */
    private static void exitDuke() {
        isBye = true;
        printLine();
        System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
    }

    /**
     * Executes the user command specified to Duke.
     *
     * @param input The command specified to Duke.
     */
    private static void execute(String input) {
        String[] strings = input.split(" ");
        Command cmd = Command.valueOf(strings[0].toUpperCase());

        switch (cmd) {
        case BYE:
            exitDuke();
            break;

        case LIST:
            displayList();
            break;

        case MARK:
            try {
                markTask(input);
            } catch (IndexOutOfBoundsException e) {
                printError("The specified task does not exist.");
            } catch (NumberFormatException | DukeException e) {
                printError("Specify which task to mark with a single integer.");
            }
            break;

        case UNMARK:
            try {
                unmarkTask(input);
            } catch (IndexOutOfBoundsException e) {
                printError("The specified task does not exist.");
            } catch (NumberFormatException | DukeException e) {
                printError("Specify which task to unmark with a single integer.");
            }
            break;

        case TODO:
            try {
                addTodo(input);
            } catch (StringIndexOutOfBoundsException e) {
                printError("The description of a todo cannot be empty.");
            }
            break;

        case DEADLINE:
            try {
                addDeadline(input);
            } catch (StringIndexOutOfBoundsException | DukeException e) {
                printError("The description of a deadline cannot be empty.");
            } catch (ArrayIndexOutOfBoundsException e) {
                printError("Use /by to provide when a deadline must be completed.");
            }
            break;

        case EVENT:
            try {
                addEvent(input);
            } catch (StringIndexOutOfBoundsException | DukeException e) {
                printError("The description of an event cannot be empty.");
            } catch (ArrayIndexOutOfBoundsException e) {
                printError("Use /at to provide when an event occurs.");
            }
            break;

        case DELETE:
            try {
                deleteTask(input);
            } catch (IndexOutOfBoundsException e) {
                printError("The specified task does not exist.");
            } catch (NumberFormatException | DukeException e) {
                printError("Specify which task to delete with a single integer.");
            }
            break;
        }
    }

    /**
     * Displays the list of tasks stored by Duke.
     */
    private static void displayList() {
        printLine();
        System.out.println(INDENTATION + "Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(INDENTATION + (i + 1) + "." + tasks.get(i));
        }
        printLine();
    }

    /**
     * Marks a task as done.
     *
     * @param input The command specified to Duke.
     * @throws DukeException when the input is just "mark".
     */
    private static void markTask(String input) throws DukeException {
        if (input.length() == 4) {
            throw new DukeException();
        }

        input = input.substring(5);
        int n = Integer.parseInt(input);
        Task specifiedTask = tasks.get(n - 1);
        specifiedTask.markAsDone();

        printLine();
        System.out.println(INDENTATION + "Nice! I've marked this task as done:\n"
                + INDENTATION + "  " + specifiedTask);
        printLine();
    }

    /**
     * Marks a task as not done.
     *
     * @param input The command specified to Duke.
     * @throws DukeException when the input is just "unmark".
     */
    private static void unmarkTask(String input) throws DukeException {
        if (input.length() == 6) {
            throw new DukeException();
        }

        input = input.substring(7);
        int n = Integer.parseInt(input);
        Task specifiedTask = tasks.get(n - 1);
        specifiedTask.unmarkAsDone();

        printLine();
        System.out.println(INDENTATION + "OK, I've marked this task as not done yet:\n"
                + INDENTATION + "  " + specifiedTask);
        printLine();
    }

    /**
     * Adds a task to be tracked and
     * prints a message that the task is added.
     *
     * @param task The specified task to be added onto the tracked list.
     */
    private static void addTask(Task task) {
        tasks.add(task);
        taskCount++;
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
     * Adds a new Todo task to Duke.
     *
     * @param input The command specified to Duke.
     */
    private static void addTodo(String input) {
        addTask(new Todo(input.substring(5)));
    }

    /**
     * Adds a new Deadline task to Duke.
     *
     * @param input The command specified to Duke.
     * @throws DukeException when command provides deadline by which
     *     task must complete but no description.
     */
    private static void addDeadline(String input) throws DukeException {
        input = input.substring(9);
        if (input.startsWith("/by")) {
            throw new DukeException();
        }
        String[] strings = input.split(" /by ");
        addTask(new Deadline(strings[0], strings[1]));
    }

    /**
     * Adds a new Event task to Duke.
     *
     * @param input The command specified to Duke.
     * @throws DukeException when command provides time at which
     *     event occurs but no description.
     */
    private static void addEvent(String input) throws DukeException {
        input = input.substring(6);
        if (input.startsWith("/at")) {
            throw new DukeException();
        }
        String[] strings = input.split(" /at ");
        addTask(new Event(strings[0], strings[1]));
    }

    /**
     * Deletes a specified task.
     *
     * @param input The command specified to Duke.
     * @throws DukeException when the command is just "delete".
     */
    private static void deleteTask(String input) throws DukeException {
        if (input.length() == 6) {
            throw new DukeException();
        }

        input = input.substring(7);
        int n = Integer.parseInt(input);
        Task specifiedTask = tasks.remove(n - 1);
        taskCount--;

        printLine();
        System.out.println(INDENTATION + "Noted. I've removed this task:\n"
                + INDENTATION + "  " + specifiedTask);
        if (taskCount == 1) {
            System.out.println(INDENTATION + "Now you have 1 task in your list.");
        } else {
            System.out.println(INDENTATION + "Now you have " + taskCount + " tasks in your list.");
        }
        printLine();
    }

    /**
     * The main method for the chatbot.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        isBye = false;
        printStart();
        Scanner sc = new Scanner(System.in);
        while (!isBye) {
            try {
                execute(sc.nextLine());
            } catch (IllegalArgumentException e) {
                printError("I'm sorry, but I don't know what that means :-(");
            }
        }
        sc.close();
    }
}
