import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Duke {
    private static final String NAME = "Duke";
    private static final ArrayList<Task> TASKS = new ArrayList<>();
    private static boolean isDone = false;
    private static final Map<String, TaskNames> STRING_TASK_NAMES_MAP = Map.of(
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
        System.out.printf("Hello! I'm %s\n", NAME);
        System.out.println("What can I do for you?");
    }

    private static void processUserCommand(Scanner myScanner) {
        // split into ["userCommand", "details"]
        String[] userInput = myScanner.nextLine().split(" ", 2);
        String userCommand = userInput[0].toLowerCase();

        try {
            switch (userCommand) {
            case "bye":
                sayGoodbye();
                break;
            case "list":
                listTasks();
                break;
            case "mark":
                getTask(userInput).markAsDone();
                break;
            case "unmark":
                getTask(userInput).markAsNotDone();
                break;
            case "delete":
                deleteTask(userInput);
                break;
            default:
                validateTask(userCommand);
                String taskDescription = getDescription(userInput);
                Task task = createTask(userCommand, taskDescription);
                addTask(task);
                break;
            }
        } catch (DukeException de) {
            System.out.println(de);
        }
    }

    private static void sayGoodbye() {
        isDone = true;
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < TASKS.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, TASKS.get(i));
        }
    }

    private static void addTask(Task task) {
        TASKS.add(task);
        System.out.printf("Got it. I've added this task:\n %s\n", task);
        sayTasksCount();
    }

    private static Task getTask(String[] userInput) throws DukeException {
        return TASKS.get(Integer.parseInt(getDescription(userInput)) - 1);
    }

    private static Task createTask(String taskName, String taskDescription) {

        String[] temp = taskDescription.split(" /");

        switch (STRING_TASK_NAMES_MAP.get(taskName)) {
        case TODO:
            return new Todo(taskDescription);
        case EVENT:
            return new Event(temp[0], temp[1].split(" ", 2)[1]);
        case DEADLINE:
            return new Deadline(temp[0], temp[1].split(" ", 2)[1]);
        default:
            // should not reach this part
            return null;
        }
    }

    private static void validateTask(String userCommand) throws DukeException {
        if (!STRING_TASK_NAMES_MAP.containsKey(userCommand)) {
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

    private static void deleteTask(String[] userInput) throws DukeException {
        Task task = getTask(userInput);
        TASKS.remove(task);
        System.out.printf("Noted. I've removed this task:\n %s\n", task);
        sayTasksCount();
    }

    private static void sayTasksCount() {
        System.out.printf("Now you have %d tasks in the list.\n", TASKS.size());
    }

}
