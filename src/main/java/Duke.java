import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Duke {

    /** The tasks stored */
    private static final List<String> tasks = new ArrayList<>();

    /** Prints Duke's greeting on opening the app */
    private static void introduceSelf() {
        sayLines(new String[] {
                "Hello! I'm Duke",
                "What can I do for you?",
        });
    }

    /** Displays the given lines using a format */
    private static void sayLines(String[] lines) {
        System.out.println("____________________________________________________________");
        for (String line : lines) {
            System.out.println(line);
        }
        System.out.println("____________________________________________________________\n");
    }

    /** Stores the given task and notifies user of outcome */
    private static void addTask(String input) {
        tasks.add(input);
        sayLines(new String[] {
                "added: " + input,
        });
    }

    /** Lists out information on all tasks stored */
    private static void listTasks() {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.println((i+1) + ". " + tasks.get(i));
        }
        System.out.println("____________________________________________________________\n");
    }

    /** Prints Duke's closing statement on exiting the app */
    private static void sayGoodbye() {
        sayLines(new String[] {
                "Bye. Hope to see you again soon!",
        });
    }

    public static void main(String[] args) {
        introduceSelf();

        Scanner inputScanner = new Scanner(System.in);
        String userInput = inputScanner.nextLine();
        while (!Objects.equals(userInput, "bye")) {
            if (Objects.equals(userInput, "list")) {
                listTasks();
            } else {
                addTask(userInput);
            }
            userInput = inputScanner.nextLine();
        }
        inputScanner.close();

        sayGoodbye();
    }
}
