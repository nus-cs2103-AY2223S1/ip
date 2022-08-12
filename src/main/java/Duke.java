import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    // For adding some colour
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static ArrayList<Task> taskList = new ArrayList<>();

    public static void printWithIndent(String toPrint) {
        System.out.println("\t" + toPrint.replace("\n", "\n\t"));
    }

    public static void printLine() {
        printWithIndent("____________________________________________________________");
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

    public static void addTask(String item) {
        Task task = new Task(item);
        taskList.add(task);
        printLine();
        printWithIndent("added: " + item);
        printLine();
    }

    public static void listTasks() {
        printLine();
        for (int i = 0; i < taskList.size(); i++) {
            Task task =  taskList.get(i);
            printWithIndent(String.valueOf(i + 1) + ". " + task);
        }
        printLine();
    }

    public static Task getTask(int index) {
        // The user gives 1-indexed numbers.
        return taskList.get(index - 1);
    }

    public static void markTask(int index) {
        Task task = getTask(index);
        task.markDone();
        printLine();
        printWithIndent("Nice! I've marked this task as done:");
        printWithIndent("  " + task);
        printLine();
    }

    public static void unmarkTask(int index) {
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
            String input = scanner.nextLine().strip();
            String[] inputArray = input.split(" +");
            String firstWord = inputArray[0];
            switch (firstWord) {
                case "bye":
                    exit();
                    stillRunning = false;
                    break;
                case "list":
                    listTasks();
                    break;
                case "mark":
                    markTask(Integer.parseInt(inputArray[1]));
                    break;
                case "unmark":
                    unmarkTask(Integer.parseInt(inputArray[1]));
                    break;
                default:
                    addTask(input);
                    break;
            }
        }
    }
}
