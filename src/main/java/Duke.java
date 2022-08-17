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
            try {
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
                        markTask(argument);
                        break;
                    case "unmark":
                        unmarkTask(argument);
                        break;
                    case "todo":
                        ToDo todo = handleToDo(argument);
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
                        throw new InvalidCommandException(command);
                }
            } catch (DukeException e) {
                generateMessage(e.toString());
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

    public static ToDo handleToDo(String information) throws EmptyArgumentException {
        if (information.isEmpty()) {
            throw new EmptyArgumentException(Commands.todo);
        }
        ToDo todo = new ToDo(information);
        return todo;
    }

    public static Deadline handleDeadline(String information) throws DukeException {
        if (information.isEmpty()) {
            throw new EmptyArgumentException(Commands.deadline);
        }
        if (!information.contains("/by")) {
            throw new InvalidArgumentException(Commands.deadline);
        }
        String[] stringArr = information.split(" /by ",2);
        Deadline deadline = new Deadline(stringArr[0], stringArr[1]);
        return deadline;
    }

    public static Event handleEvent(String information) throws DukeException {
        if (information.isEmpty()) {
            throw new EmptyArgumentException(Commands.event);
        }
        if (!information.contains("/at")) {
            throw new InvalidArgumentException(Commands.event);
        }
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

    public static void markTask(String information) throws DukeException {
        if (information.isEmpty()) {
            throw new EmptyArgumentException(Commands.mark);
        }
        if (!information.chars().allMatch( Character :: isDigit )) {
            throw new InvalidArgumentException(Commands.mark);
        }
        int index = Integer.parseInt(information) - 1;
        if (index < 0 || index >= storage.size()) {
            throw new MarkException();
        }
        Task task = storage.get(index);
        task.markAsDone();
        generateMessage("Nice! I've marked this task as done:\n" + task);
    }

    public static void unmarkTask(String information) throws DukeException {
        if (information.isEmpty()) {
            throw new EmptyArgumentException(Commands.mark);
        }
        if (!information.chars().allMatch( Character :: isDigit )) {
            throw new InvalidArgumentException(Commands.mark);
        }
        int index = Integer.parseInt(information) - 1;
        if (index < 0 || index >= storage.size()) {
            throw new MarkException();
        }
        Task task = storage.get(index);
        task.markAsIncomplete();
        generateMessage("OK, I've marked this task as not done yet:\n" + task);
    }
}
