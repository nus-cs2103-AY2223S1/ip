import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Personal Assistant that helps you keep track of your tasks
 */
public class Duke {

    /** The tasks stored */
    private static final List<Task> tasks = new ArrayList<>();

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

    /** Stores a task based on the given description and notifies user of outcome */
    private static void addTask(String description) {
        tasks.add(new Task(description));
        sayLines(new String[] {
                "added: " + description,
        });
    }

    /** Marks task taskNumber as done and notifies user of outcome */
    private static void markTaskAsDone(int taskNumber) {
        //cases to handle:
        //taskNumber >= tasks.size()
        //task is alr done
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        sayLines(new String[] {
                "Nice! I've marked this task as done:",
                "  " + task
        });
    }

    /** Marks task taskNumber as not done and notifies user of outcome */
    private static void markTaskAsNotDone(int taskNumber) {
        //cases to handle:
        //taskNumber >= tasks.size()
        //task is alr not done
        Task task = tasks.get(taskNumber - 1);
        task.markAsNotDone();
        sayLines(new String[] {
                "OK, I've marked this task as not done yet:",
                "  " + task
        });
    }

    /** Lists out information on all tasks stored */
    private static void listTasks() {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.println((i+1) + "." + tasks.get(i));
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
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                listTasks();
            } else if (userInput.length() >= 6 && userInput.startsWith("mark ")) {
                markTaskAsDone(Integer.parseInt(userInput.substring(5)));
            } else if (userInput.length() >= 8 && userInput.startsWith("unmark ")) {
                markTaskAsNotDone(Integer.parseInt(userInput.substring(7)));
            } else {
                addTask(userInput);
            }
            userInput = inputScanner.nextLine();
        }
        inputScanner.close();

        sayGoodbye();
    }
}
