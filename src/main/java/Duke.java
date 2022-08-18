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
        if (input == "") {
            System.out.println("Invalid Input");
            return;
        }

        String[] words = input.toLowerCase().split(" ");
        String command = words[0];
        List<String> args = ParseArgs(words);

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
            default:
                AddToList(input);
        }
    }

    /**
     * Parses an array of words into a list of arguments, omitting the first word as the command
     *
     * @param words - the array of string to parse
     * @return a list of strings that represent the arguments
     */
    public static List<String> ParseArgs(String[] words) {
        List<String> args = new ArrayList<>();
        for (int i = 1; i < words.length; i++) {
            args.add(words[i]);
        }
        return args;
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
     * Creates a task from the given string and adds it to the list, which is displayed when DisplayList is called
     *
     * @param text - the text to add to the list
     */
    public static void AddToList(String text) {
        taskList.add(new Task(text, false));
        System.out.printf("added: %s\n", text);
    }

    /**
     * Displays the list of tasks added to the list so far in order
     */
    public static void DisplayList() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, taskList.get(i));
        }
    }

    /**
     * Try to mark or unmark a task based on the parsed task index
     *
     * @param marked - if true, attempt to mark the task, otherwise attempt to unmark
     * @param args - a list of string inputs, the first of which will be parsed as the task index to mark/unmark
     */
    public static void TryMark(boolean marked, List<String> args) {
        if (args.size() == 0) {
            System.out.println("Mark failed, no index given");
            return;
        }

        String indexString = args.get(0);
        int index;
        try {
            index = Integer.parseInt(indexString);
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
            System.out.println("Task marked as done");
            task.mark();
        } else {
            System.out.println("Task marked as not done");
            task.unmark();
        }
        System.out.println(task);
    }
}
