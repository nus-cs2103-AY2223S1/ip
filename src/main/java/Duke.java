import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static class DukeException extends Exception {
        private final String message;
        public DukeException(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return this.message;
        }
    }

    // For adding some colour
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static ArrayList<Task> taskList = new ArrayList<>();

    public static void printWithIndent(String toPrint) {
        System.out.println("\t" + toPrint.replace("\n", "\n\t"));
    }

    public static void printLine() {
        printWithIndent("____________________________________________________________");
    }

    public static void printError(String error) {
        printWithIndent(ANSI_RED + "☹ Oh no! " + error + ANSI_RESET);
    }

    public static void printTaskCount() {
        printWithIndent(String.format("Now you have %d tasks in the list.", taskList.size()));
    }

    public static int parseInt(String number) throws DukeException {
        try {
            return Integer.parseInt(number);
        } catch (java.lang.NumberFormatException e) {
            throw new DukeException(String.format("%s is not a number. e.g 5 is a number, five is a string.", number));
        }
    }
    public static void greet() {
        printLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        printWithIndent("Hello from\n" + ANSI_CYAN + logo + ANSI_RESET);
        printWithIndent("How can I help you today?");
        printLine();
    }

    public static void echo(String input) {
        printLine();
        printWithIndent(input);
        printLine();
    }

    public static void exit() {
        printLine();
        printWithIndent("Bye. Hope to see you again soon!");
        printLine();
    }

    public static Todo parseTodo(String description) throws DukeException {
        if (description.length() == 0) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        return new Todo(description);
    }

    public static Deadline parseDeadline(String argsString) throws DukeException {
        String[] args = argsString.split(" */by *");
        if (args.length != 2) {
            throw new DukeException("Wrong usage of deadline.\nUsage: deadline some description /by some date");
        }
        String description = args[0];
        String by = args[1];
        if (description.length() == 0) {
            throw new DukeException("Description should not be empty.");
        }

        return new Deadline(description, by);
    }

    public static Event parseEvent(String argsString) throws DukeException {
        String[] args = argsString.split(" */at *");
        if (args.length != 2) {
            throw new DukeException("Wrong usage of event.\nUsage: event some description /at some date");
        }
        String description = args[0];
        if (description.length() == 0) {
            throw new DukeException("Description should not be empty.");
        }
        String at = args[1];
        return new Event(description, at);

    }
    public static void addTask(String argsString, int type) throws DukeException {
        Task task;
        switch (type) {
            // Todo
            case 0:
                task = parseTodo(argsString);
                break;
            // Deadline
            case 1:
                task = parseDeadline(argsString);
                break;
            // Event
            case 2:
                task = parseEvent(argsString);
                break;
            default:
                throw new IllegalArgumentException("Invalid Task Type");
        }
        taskList.add(task);
        printLine();
        printWithIndent("Got it. I've added this task:");
        printWithIndent(" " + task);
        printTaskCount();
        printLine();
    }

    public static void listTasks() {
        printLine();
        for (int i = 0; i < taskList.size(); i++) {
            Task task =  taskList.get(i);
            printWithIndent(i + 1 + ". " + task);
        }
        printLine();
    }

    public static Task getTask(int index) throws DukeException {
        int numTasks = taskList.size();

        if (numTasks == 0) {
            throw new DukeException("You don't have tasks.");
        }
        if (index < 1) {
            throw new DukeException("Task number should be at least 1.");
        }
        if (index > numTasks) {
            throw new DukeException(String.format("You only have %d tasks.", numTasks));
        }

        // The user gives 1-indexed numbers.
        return taskList.get(index - 1);
    }

    public static void markTask(int index) throws DukeException {
        Task task = getTask(index);
        task.markDone();
        printLine();
        printWithIndent("Nice! I've marked this task as done:");
        printWithIndent("  " + task);
        printLine();
    }

    public static void unmarkTask(int index) throws DukeException {
        Task task = getTask(index);
        task.unmarkDone();
        printLine();
        printWithIndent("OK, I've marked this task as not done yet:");
        printWithIndent("  " + task);
        printLine();
    }

    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        boolean stillRunning = true;
        while (stillRunning) {
            try {
                // Partially Parse Input
                String input = scanner.nextLine().strip();
                String[] inputArray = input.split(" +", 2);
                String firstWord = inputArray[0];
                String argsString = "";
                if (inputArray.length == 2) {
                    argsString = inputArray[1];
                }

                // commands
                switch (firstWord) {
                    case "bye":
                        exit();
                        stillRunning = false;
                        break;
                    case "list":
                        listTasks();
                        break;
                    case "mark":
                        markTask(parseInt(argsString));
                        break;
                    case "unmark":
                        unmarkTask(parseInt(argsString));
                        break;
                    case "todo":
                        addTask(argsString, 0);
                        break;
                    case "deadline":
                        addTask(argsString, 1);
                        break;
                    case "event":
                        addTask(argsString, 2);
                        break;
                    default:
                        throw new DukeException("I don't know this command!");
                }
            } catch (DukeException exception) {
                printLine();
                printError(exception.toString());
                printLine();
            }
        }
    }
}
