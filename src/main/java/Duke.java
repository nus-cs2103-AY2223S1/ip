import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    protected static final String topWindow = "╔═══════════════════════════════════════════╗";
    protected static final String bottomWindow = "╚═══════════════════════════════════════════╝";
    private static final String greeting = "Hi, I'm Ploopy! Nice to meet you!\n\tWhats up?";
    private static final String farewell = "Okay then, see ya later :)";
    private static final String completedTask = "Nice! You've completed this task. I'll mark it as done.";
    private static final String incompleteTask = "Alright this task has been marked as undone.";
    private static final String addedTask = "I've added this task to your list.\n\tHere you go: ";
    private static int totalTasks = 0;

    //Exceptions:
    private static final String NoInputMessage = "You didn't say what I should do! (ಠ_ʖಠ)";
    private static final String EmptyCommandMessage = "What should I do with the ";
    private static final String NonsenseInputMessage = "I can't do that right now, sorry ┐(‘～`；)┌";

    public static final String textArt = "\n" +
            "\t███████████████████████████████████\n" +
            "\t█▄─▄▄─█▄─▄███─▄▄─█─▄▄─█▄─▄▄─█▄─█─▄█\n" +
            "\t██─▄▄▄██─██▀█─██─█─██─██─▄▄▄██▄─▄██\n" +
            "\t▀▄▄▄▀▀▀▄▄▄▄▄▀▄▄▄▄▀▄▄▄▄▀▄▄▄▀▀▀▀▄▄▄▀▀";

    private static ArrayList<Task> tasks;

    public static void start() {
        System.out.println(messageFormatter(textArt));
        System.out.println(messageFormatter(greeting));
        tasks = new ArrayList<>();
        command();
    }

    protected static String messageFormatter(String input) {
        return topWindow + "\n\t" + input + "\n" +bottomWindow;
    }

    private static void displayList() {
        System.out.println(topWindow);
        int index = 1;
        for (Task item : tasks) {
            System.out.println("\t" + index + "." + item);
            index++;
        }
        System.out.println(bottomWindow);
    }

    private static void markTask(int taskIndex) {
        Task current = tasks.get(taskIndex - 1);
        current.markDone();
        System.out.println(messageFormatter(completedTask + "\n\t" + " " + current));
    }

    private static void unmarkTask(int taskIndex) {
        Task current = tasks.get(taskIndex - 1);
        current.unmark();
        System.out.println(messageFormatter(incompleteTask + "\n\t" + " " + current));
    }

    private static void deleteTask(int taskNumber) {
        if (taskNumber > 0 && taskNumber <= totalTasks)  {
            totalTasks--;
            String message = "Deleted: " + tasks.get(taskNumber - 1) + "\n\tYou have "
                    + totalTasks + " task(s) remaining.";
            tasks.remove(taskNumber - 1);
            System.out.println(messageFormatter(message));
        }

    }

    private static void addTaskMessage(Task task) {
        totalTasks++;
        String message = addedTask + task.toString() + "\n\tYou have " + totalTasks + " tasks in your list.";
        System.out.println(messageFormatter(message));
    }

    private static boolean isEmptyCommand(String command, int size) {
        // this method removes the whitespace of a command and checks if its length = size
        // true means empty command
        return command.trim().length() == size;
    }

    private static void createToDo(String input) throws DukeException {
        if (!isEmptyCommand(input, "todo".length())) {
            Task newTask = Task.createTask(input.split("todo")[1].trim(), null,"todo");
            tasks.add(newTask);
            addTaskMessage(newTask);
        } else throw new DukeException(messageFormatter(EmptyCommandMessage + " todo"));
    }

    private static void createDeadline(String input) throws DukeException {
        if (!isEmptyCommand(input, "deadline".length())) {
            String date = input.split("/")[1];
            String name = input.split(" ")[1];
            Task newTask = Task.createTask(name, date, "deadline");
            tasks.add(newTask);
            addTaskMessage(newTask);
        } else throw new DukeException(messageFormatter(EmptyCommandMessage + " deadline"));
    }

    private static void createEvent(String input) throws DukeException {
        if (!isEmptyCommand(input, "event".length())) {
            String date = input.split("/")[1];
            String name = input.split(" ")[1];
            Task newTask = Task.createTask(name, date, "event");
            tasks.add(newTask);
            addTaskMessage(newTask);
        } else throw new DukeException(messageFormatter(EmptyCommandMessage + " event"));
    }

    private static void command() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String command;
        while(!input.equals("bye")) {
            try {
                if (input.isBlank() || input.isEmpty()) throw new DukeException(messageFormatter(NoInputMessage));
                command = input.split(" ")[0];
                switch (command) {
                    case "mark":
                        markTask(input.charAt(5) - 48);
                        break;
                    case "unmark":
                        unmarkTask(input.charAt(7) - 48);
                        break;
                    case "list":
                        displayList();
                        break;
                    case "delete":
                        deleteTask(input.charAt(7) - 48);
                        break;
                    case "todo":
                        createToDo(input);
                        break;
                    case "deadline":
                        createDeadline(input);
                        break;
                    case "event":
                        createEvent(input);
                        break;
                    default: throw new DukeException(messageFormatter(NonsenseInputMessage));
                }
            } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
            input = scanner.nextLine();
        }
        System.out.println(messageFormatter(farewell));
    }
    
    public static void main(String[] args) {
      start();
    }
}
