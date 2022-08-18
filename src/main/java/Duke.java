import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private static final String topWindow = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n \t";
    private static final String bottomWindow = "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private static final String greeting = "Hi, I'm Ploopy! Nice to meet you!\n\tWhats up?";
    private static final String farewell = "Okay then, see ya later :)";
    private static final String completedTask = "Nice! You've completed this task. I'll mark it as done.";
    private static final String incompleteTask = "Alright this task has been marked as undone.";
    private static final String addedTask = "I've added this task to your list.\n\tHere you go: ";

//    private enum COMMAND {
//        list,
//        bye,
//        todo,
//        deadline,
//        event
//    }

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

    private static void messageFormatter(String input) {
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

    private static void command() {
        Scanner scanner = new Scanner(System.in);
        StringBuilder input = new StringBuilder(scanner.nextLine());
        int end;

        while(!input.toString().equals("bye")) {
            if (input.toString().matches("\\bmark\\s\\d+\\b")) {
                markTask(input.charAt(5) - 48);
            } else if (input.toString().matches("\\bunmark\\s\\d+\\b")) {
                unmarkTask(input.charAt(7) - 48);
            } else if (input.toString().equals("list")) {
                displayList();
            } else if (input.toString().matches("\\btodo\\s.*\\b")) {
                System.out.println(input.substring(4, input.length()));
                Task newTask = Task.createTask(input.substring(4, input.length()), null, "todo");
                tasks.add(newTask);
                addTaskMessage(newTask);
            } else if (input.toString().matches("\\bdeadline\\s.*\\b")) {
                end = input.indexOf("/");
                String name = input.substring(8, end);
                String date = input.substring(input.lastIndexOf("/") + 1);
                Task newTask = Task.createTask(name, date, "deadline");
                tasks.add(newTask);
                addTaskMessage(newTask);
            } else if (input.toString().matches("\\bevent\\s.*\\b")) {
                end = input.indexOf("/");
                String name = input.substring(6, end);
                String date = input.substring(input.lastIndexOf("/") + 1);
                Task newTask = Task.createTask(name, date, "event");
                tasks.add(newTask);
                addTaskMessage(newTask);
            }

            input.replace(0, input.length(), scanner.nextLine()); //replacing content
        }
        messageFormatter(farewell);
    }
    
    public static void main(String[] args) {
        start();
    }
}
