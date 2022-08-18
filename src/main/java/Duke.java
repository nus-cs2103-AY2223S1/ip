import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Duke {
    private static final String name = "Duke";
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static boolean isDone = false;
    private static final Map<String, TaskNames> stringTaskNamesHashMap = Map.of(
        "todo", TaskNames.TODO,
        "deadline", TaskNames.DEADLINE,
        "event", TaskNames.EVENT
    );

    public static void main(String[] args) {
        greet();
        Scanner myScanner = new Scanner(System.in);

        while (!isDone) {
            processUserCommand(myScanner);
        }
    }

    private static void greet() {
        System.out.printf("Hello! I'm %s\n", name);
        System.out.println("What can I do for you?");
    }

    private static void processUserCommand(Scanner myScanner) {
        String userCommand = myScanner.nextLine();

        if (userCommand.equalsIgnoreCase("bye")) {
            sayGoodbye();
            isDone = true;
        } else if (userCommand.equalsIgnoreCase("list")) {
            listUserCommands();
        } else if (userCommand.toLowerCase().startsWith("mark")) {
            getTask(userCommand).markAsDone();
        } else if (userCommand.toLowerCase().startsWith("unmark")) {
            getTask(userCommand).markAsNotDone();
        } else {
            String[] taskDetails = userCommand.split(" ", 2);
            Task task = createTask(taskDetails[0], taskDetails[1]);
            addToList(task);
        }
    }

    private static void sayGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void listUserCommands() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, tasks.get(i));
        }
    }

    private static void addToList(Task task) {
        tasks.add(task);
        System.out.printf("Got it. I've added this task:\n %s\n", task);
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
    }

    private static Task getTask(String userCommand) {
        return tasks.get(Integer.parseInt(userCommand.split(" ")[1]) - 1);
    }

    private static Task createTask(String taskName, String taskDescription) {
        String[] temp = taskDescription.split(" /");

        switch (stringTaskNamesHashMap.get(taskName)) {
            case TODO:
                return new Todo(taskDescription);
            case EVENT:
                return new Event(temp[0], temp[1].split(" ", 2)[1]);
            case DEADLINE:
                return new Deadline(temp[0], temp[1].split(" ", 2)[1]);
            default:
                return null;
        }
    }

}
