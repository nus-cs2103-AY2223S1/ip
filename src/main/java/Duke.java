import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    protected static final String topWindow = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\t";
    protected static final String bottomWindow = "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private static final String greeting = "Hi, I'm Ploopy! Nice to meet you!\n\tWhats up?";
    private static final String farewell = "Okay then, see ya later :)";
    private static final String completedTask = "Nice! You've completed this task. I'll mark it as done.";
    private static final String incompleteTask = "Alright this task has been marked as undone.";
    private static final String addedTask = "I've added this task to your list.\n\tHere you go: ";

    public static final String textArt = "\n" +
            "███████████████████████████████████\n" +
            "█▄─▄▄─█▄─▄███─▄▄─█─▄▄─█▄─▄▄─█▄─█─▄█\n" +
            "██─▄▄▄██─██▀█─██─█─██─██─▄▄▄██▄─▄██\n" +
            "▀▄▄▄▀▀▀▄▄▄▄▄▀▄▄▄▄▀▄▄▄▄▀▄▄▄▀▀▀▀▄▄▄▀▀";

    private static ArrayList<Task> tasks;

    public static void start() {
        messageFormatter(textArt);
        messageFormatter(greeting);
        tasks = new ArrayList<>();
        command();
    }

    protected static void messageFormatter(String input) {
        System.out.println(topWindow + input + bottomWindow);
    }

    private static void displayList() {
        System.out.println(topWindow);
        for (Task item : tasks) {
            System.out.println("\n \t" + item);
        }
        System.out.println(bottomWindow);
    }

    private static void markTask(int taskIndex) {
        Task current = tasks.get(taskIndex - 1);
        current.markDone();
        messageFormatter(completedTask + "\n\t" + " " + current);
    }

    private static void unmarkTask(int taskIndex) {
        Task current = tasks.get(taskIndex - 1);
        current.unmark();
        messageFormatter(incompleteTask + "\n\t" + " " + current);
    }

    private static void addTaskMessage(Task task) {
        String message = addedTask + task.toString() + "\n\tYou have " + tasks.size() + " tasks in your list.";
        messageFormatter(message);
    }

    private static boolean isEmptyCommand(String command, int size) {
        // this method removes the whitespace of a command and checks if its length = size
        // true means empty command
        return command.trim().length() == size;
    }

    private static void command() {
        Scanner scanner = new Scanner(System.in);
        StringBuilder input = new StringBuilder(scanner.nextLine());

        while(!input.toString().equals("bye")) {
        try {
            if (input.toString().matches("\\bmark\\s\\d+\\b")) {

                markTask(input.charAt(5) - 48);
            } else if (input.toString().matches("\\bunmark\\s\\d+\\b")) {
                unmarkTask(input.charAt(7) - 48);
            } else if (input.toString().equals("list")) {
                displayList();
            } else if (input.length() > 3 && input.substring(0, 4).equals("todo")) {
                if (!isEmptyCommand(input.toString(), "todo".length())) {
                    Task newTask = Task.createTask(input.substring(5, input.length()), null, "todo");
                    tasks.add(newTask);
                    addTaskMessage(newTask);
                } else throw DukeException.ExceptionCreator("BlankCommand", "todo");
            } else if (input.length() > 7 && input.substring(0, 8).equals("deadline"))  {
                if (!isEmptyCommand(input.toString(), "deadline".length())) {
                    int end = input.indexOf("/");
                    String name = input.substring(8, end);
                    String date = input.substring(input.lastIndexOf("/") + 1);
                    Task newTask = Task.createTask(name, date, "deadline");
                    tasks.add(newTask);
                    addTaskMessage(newTask);
                } else throw DukeException.ExceptionCreator("BlankCommand", "deadline");
            } else if (input.length() > 4 && input.substring(0, 5).equals("event"))  {
                if (!isEmptyCommand(input.toString(), "event".length())) {
                    int end = input.indexOf("/");
                    String name = input.substring(6, end);
                    String date = input.substring(input.lastIndexOf("/") + 1);
                    Task newTask = Task.createTask(name, date, "event");
                    tasks.add(newTask);
                    addTaskMessage(newTask);
                } else throw DukeException.ExceptionCreator("BlankCommand", "event");
            } else {
                throw DukeException.ExceptionCreator("InvalidCommand", null);
            }

        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
            input.replace(0, input.length(), scanner.nextLine()); //replacing content
        }
        messageFormatter(farewell);
    }
    
    public static void main(String[] args) {
      start();
    }
}
