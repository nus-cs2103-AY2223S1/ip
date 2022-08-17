import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Personal Assistant that helps you keep track of your tasks
 */
public class Duke {

    /** The tasks stored */
    private static final List<Task> tasks = new ArrayList<>();

    /**
     * Prints Duke's greeting on opening the app
     */
    private static void introduceSelf() {
        sayLines(new String[] {
                "Hello! I'm Duke",
                "What can I do for you?",
        });
    }

    /**
     * Displays the given lines using a format
     */
    private static void sayLines(String[] lines) {
        System.out.println("____________________________________________________________");
        for (String line : lines) {
            System.out.println(line);
        }
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Stores the given task and notifies user of outcome
     */
    private static void addTask(Task task) {
        tasks.add(task);
        sayLines(new String[] {
                "Got it. I've added this task:",
                "  " + task,
                "Now you have " + tasks.size() + " task" + (tasks.size() > 1 ? "s" : "") + " in the list."
        });
    }

    /**
     * Tries to store a Todo with the given specifications and notifies user of outcome
     * @param userInput The command entered, always starts with "todo "
     */
    private static void addTodo(String userInput) {
        int startOfDescArg = "todo ".length();
        addTask(new Todo(userInput.substring(startOfDescArg)));
    }

    /**
     * Tries to store a Deadline with the given specifications and notifies user of outcome
     * @param userInput The command entered, always starts with "deadline "
     */
    private static void addDeadline(String userInput) {
        int startOfDescArg = "deadline ".length();
        int startOfByFlag = userInput.indexOf(" /by ");
        addTask(new Deadline(userInput.substring(startOfDescArg, startOfByFlag),
                userInput.substring(startOfByFlag + 5)));
    }

    /**
     * Tries to store an Event with the given specifications and notifies user of outcome
     * @param userInput The command entered, always starts with "event "
     */
    private static void addEvent(String userInput) {
        int startOfDescArg = "event ".length();
        int startOfAtFlag = userInput.indexOf(" /at ");
        addTask(new Event(userInput.substring(startOfDescArg, startOfAtFlag),
                userInput.substring(startOfAtFlag + 5)));
    }

    /**
     * Tries to mark the specified task as done and notifies user of outcome
     * @param userInput The command entered, always starts with "mark "
     */
    private static void markTaskAsDone(String userInput) {
        //cases to handle:
        //taskNumber >= tasks.size()
        //task is alr done
        int taskNumber = Integer.parseInt(userInput.substring(5));
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        sayLines(new String[] {
                "Nice! I've marked this task as done:",
                "  " + task
        });
    }

    /**
     * Tries to mark the specified task as not done and notifies user of outcome
     * @param userInput The command entered, always starts with "unmark "
     */
    private static void markTaskAsNotDone(String userInput) {
        //cases to handle:
        //taskNumber >= tasks.size()
        //task is alr not done
        int taskNumber = Integer.parseInt(userInput.substring(7));
        Task task = tasks.get(taskNumber - 1);
        task.markAsNotDone();
        sayLines(new String[] {
                "OK, I've marked this task as not done yet:",
                "  " + task
        });
    }

    /**
     * Lists out information on all tasks stored
     */
    private static void listTasks() {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.println((i+1) + "." + tasks.get(i));
        }
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Prints Duke's closing statement on exiting the app
     */
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
            } else if (userInput.startsWith("todo ")) {
                addTodo(userInput);
            } else if (userInput.startsWith("deadline ")) {
                addDeadline(userInput);
            } else if (userInput.startsWith("event ")) {
                addEvent(userInput);
            } else if (userInput.startsWith("mark ")) {
                markTaskAsDone(userInput);
            } else if (userInput.startsWith("unmark ")) {
                markTaskAsNotDone(userInput);
            } else {
                sayLines(new String[] {
                        "I'm sorry but I don't know what that means"
                });
            }
            userInput = inputScanner.nextLine();
        }
        inputScanner.close();

        sayGoodbye();
    }
}
