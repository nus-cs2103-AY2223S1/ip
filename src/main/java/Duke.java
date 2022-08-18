import java.util.ArrayList;
import java.util.Locale;
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

        myScanner.close();
    }

    private static void greet() {
        System.out.printf("Hello! I'm %s\n", name);
        System.out.println("What can I do for you?");
    }

    private static void processUserCommand(Scanner myScanner) {
        // split into ["userCommand", "details"]
        String[] userInput = myScanner.nextLine().split(" ", 2);
        String userCommand = userInput[0].toLowerCase();

        try {
            if (userCommand.equals("bye")) {
                goodbye();
            } else if (userCommand.equals("list")) {
                listUserCommands();
            } else if (userCommand.equals("mark")) {
                getTask(userInput).markAsDone();
            } else if (userCommand.equals("unmark")) {
                getTask(userInput).markAsNotDone();
            } else {
                validateTask(userCommand);
                String taskDescription = getDescription(userInput);
                Task task = createTask(userCommand, taskDescription);
                addToList(task);
            }
        } catch (DukeException de) {
            System.out.println(de);
        }
    }

    private static void goodbye() {
        isDone = true;
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

    private static Task getTask(String[] userInput) throws DukeException {
        return tasks.get(Integer.parseInt(getDescription(userInput)) - 1);
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

    private static void validateTask(String userCommand) throws DukeException {
        if (!stringTaskNamesHashMap.containsKey(userCommand)) {
            throw new DukeException("I'm sorry, but I don't know what that means.");
        }
    }

    private static String getDescription(String[] userInput) throws DukeException {
        if (userInput.length < 2) {
            throw new DukeException(
                String.format("The description of %s cannot be empty.", userInput[0]));
        }

        return userInput[1];
    }

}
