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
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                generateMessage(farewell);
                break;
            } else if (input.equals("list")) {
                generateMessage(displayTasks());
            } else {
                Task temp = new Task(input);
                storage.add(temp);
                generateMessage("Added: " + input);
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

    public static String displayTasks() {
        int i = 1;
        String display = "";
        for (Task task : storage) {
            display += "\n" + i + ". " + task;
            i++;
        }
        return display;
    }

}
