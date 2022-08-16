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
                case "todo":
                    ToDo todo = new ToDo(argument);
                    addTask(todo);
                    break;
                case "deadline":
                    Deadline deadline = handleDeadline(argument);
                    addTask(deadline);
                    break;
                case "event":
                    Event event = handleEvent(argument);
                    addTask(event);
                    break;
                default:
                    Task temp = new Task(input);
                    addTask(temp);
                    break;
            }
        }
    }

    public static void addTask(Task task) {
        storage.add(task);
        String message = "Got it. I've added this task:\n"
                + "\t" + task
                + "\nNow you have " + storage.size() + " tasks in the list.";
        generateMessage(message);
    }

    public static Deadline handleDeadline(String information) {
        String[] stringArr = information.split(" /by ",2);
        Deadline deadline = new Deadline(stringArr[0], stringArr[1]);
        return deadline;
    }

    public static Event handleEvent(String information) {
        String[] stringArr = information.split(" /at ",2);
        Event event = new Event(stringArr[0], stringArr[1]);
        return event;
    }

    public static void generateMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println("Duke \uD83D\uDE0E says: ");
        System.out.println(message);
        System.out.println("____________________________________________________________");
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
