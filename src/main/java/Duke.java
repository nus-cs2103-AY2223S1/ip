import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static String greeting = "Hello, I'm\n" + logo + "How can I help you today?";

    private static String farewell = "Goodbye! Hope to see you again!";

    private static ArrayList<Task> storage = new ArrayList<>();

    public static void main(String[] args) {
        generateMessage(greeting);
        Scanner scanner = new Scanner(System.in);
        boolean active = true;
        while (active) {
            String input = scanner.nextLine();
            String[] inputArray = input.split(" ",2);
            String command = inputArray[0];
            String argument = "";
            if (inputArray.length == 2) {
                argument = inputArray[1];
            }

            switch(command) {
                case "bye":
                    generateMessage(farewell);
                    active = false;
                    break;
                case "list":
                    displayTasks();
                    break;
                case "mark":
                    int index = Integer.parseInt(argument) - 1;
                    markTask(storage.get(index));
                    break;
                case "unmark":
                    int index2 = Integer.parseInt(argument) - 1;
                    unmarkTask(storage.get(index2));
                    break;
                default:
                    Task temp = new Task(input);
                    storage.add(temp);
                    generateMessage("Added: " + input);
                    break;
            }
        }
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void generateMessage(String message) {
        printLine();
        System.out.println("Duke \uD83D\uDE0E says: ");
        System.out.println(message);
        printLine();
    }

    public static void displayTasks() {
        int i = 1;
        String display = "Here are the tasks in your list: ";
        for (Task task : storage) {
            display += "\n" + i + ". " + task;
            i++;
        }
        generateMessage(display);
    }

    public static void markTask(Task task) {
        task.markAsDone();
        generateMessage("Nice! I've marked this task as done:\n" + task);
    }

    public static void unmarkTask(Task task) {
        task.markAsIncomplete();
        generateMessage("OK, I've marked this task as not done yet:\n" + task);
    }
}
