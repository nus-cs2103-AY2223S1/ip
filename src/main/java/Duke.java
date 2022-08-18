import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    private static String botName = "Duke";
    private static int lineLength = 80;
    private static List<Task> taskList = new ArrayList<>();
    private static boolean isRunning;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.printf("Hello, I'm %s\n", botName);
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);

        isRunning = true;
        while (isRunning) {
            GenerateLine(lineLength);
            String input = scanner.nextLine();
            GenerateLine(lineLength);

            ParseInput(input);
        }

        System.out.println("Goodbye.");
    }

    /**
     * Parses an input string and calls the relevant method (if any)
     * Calls AddToList by default
     *
     * @param input - the input string to be parsed
     */
    public static void ParseInput(String input) {
        String[] words = input.toLowerCase().split(" ", 2);
        String command = words[0];
        String args = "";
        if (words.length > 1) {
            args = words[1];
        }

        switch (command) {
            case "bye":
                isRunning = false;
                break;
            case "list":
                DisplayList();
                break;
            case "mark":
                TryMark(true, args);
                break;
            case "unmark":
                TryMark(false, args);
                break;
            case "todo":
                TryAddToDo(args);
                break;
            case "deadline":
                TryAddDeadline(args);
                break;
            case "event":
                TryAddEvent(args);
                break;
            default:
                System.out.println("Command not recognised");
        }
    }

    /**
     * Prints a line of a specified character length
     *
     * @param length - the character length of the line to print
     */
    public static void GenerateLine(int length) {
        System.out.println(String.format("%" + length + "s", "").replace(" ", "-"));
    }

    /**
     * Adds a task to the list which is displayed when DisplayList is called
     *
     * @param task - the task to add to the list
     */
    public static void AddToList(Task task) {
        taskList.add(task);
        System.out.printf("Task added: %s\n", task);
        System.out.printf("You now have %d task(s) in the list\n", taskList.size());
    }

    /**
     * Creates a ToDo task from the given argument string
     *
     * @param args - the argument string to be parsed
     */
    public static void TryAddToDo(String args) {
        if (args == "") {
            System.out.println("Failed to create todo: No task name given");
            return;
        }

        AddToList(new ToDo(args, false));
    }

    /**
     * Creates a Deadline task from the given argument string
     *
     * @param args - the argument string to be parsed
     */
    public static void TryAddDeadline(String args) {
        String[] argsArr = args.split(" /by ", 2);
        if (argsArr.length < 2) {
            System.out.println("Failed to create deadline: Invalid number of arguments");
            return;
        }

        String name = argsArr[0].strip();
        String date = argsArr[1].strip();

        if (name == "") {
            System.out.println("Failed to create deadline: No task name given");
            return;
        }

        if (date == "") {
            System.out.println("Failed to create deadline: No date given");
            return;
        }

        AddToList(new Deadline(name, false, date));
    }

    /**
     * Creates an Event task from the given argument string
     *
     * @param args - the argument string to be parsed
     */
    public static void TryAddEvent(String args) {
        String[] argsArr = args.split(" /at ", 2);
        if (argsArr.length < 2) {
            System.out.println("Failed to create event: Invalid number of arguments");
            return;
        }

        String name = argsArr[0].strip();
        String date = argsArr[1].strip();

        if (name == "") {
            System.out.println("Failed to create event: No task name given");
            return;
        }

        if (date == "") {
            System.out.println("Failed to create event: No date given");
            return;
        }

        AddToList(new Event(name, false, date));
    }

    /**
     * Displays the list of tasks added to the list so far in order
     */
    public static void DisplayList() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, taskList.get(i));
        }

        System.out.printf("You have %d task(s) in your list\n", taskList.size());
    }

    /**
     * Try to mark or unmark a task based on the parsed task index
     *
     * @param marked - if true, attempt to mark the task, otherwise attempt to unmark
     * @param args - a list of string inputs, the first of which will be parsed as the task index to mark/unmark
     */
    public static void TryMark(boolean marked, String args) {
        int index;
        try {
            index = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            System.out.println("Mark failed, invalid index");
            return;
        }
        index--;

        if (index < 0 || index >= taskList.size()) {
            System.out.println("Mark failed, index out of range");
            return;
        }

        Task task = taskList.get(index);
        if (marked) {
            task.mark();
            System.out.printf("Task marked as done: %s\n", task);
        } else {
            task.unmark();
            System.out.printf("Task marked as not done: %s\n", task);
        }
    }
}
