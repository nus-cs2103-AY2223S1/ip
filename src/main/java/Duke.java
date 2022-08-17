import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREET_MESSAGE = "Hello! I am Duke! \n" + "What can I do for you?";
    private static final String EXIT_MESSAGE = "Bye! Hope to see you again soon!";
    private static final String INDEX_OUT_OF_BOUNDS_MESSAGE = "Sorry! The task index is out of bounds. Please try again with a valid index.";

    private static void printMessage(String message) {
        System.out.println(">> " + message);
    }

    private static void greet() {
        Duke.printMessage("Hello from\n" + LOGO);
        Duke.printMessage(GREET_MESSAGE);
    }

    private static String[] getUserCommand(Scanner sc) {
        System.out.print("<< ");
        String[] commands = sc.nextLine().strip().split(" ");
        return commands;
    }

    private static String listTasks(List<Task> tasks) {
        StringBuilder output = new StringBuilder();
        output.append("Here are your tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            output.append(String.format("\t %d. %s", i + 1, tasks.get(i)));
            if (i + 1 < tasks.size()) {
                output.append("\n");
            }
        }
        return output.toString();
    }

    private static void markTask(List<Task> tasks, int taskIndex, boolean isDone) {
        tasks.get(taskIndex).setIsDone(isDone);
    }

    private static void run() {
        Duke.greet();

        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        boolean terminated = false;

        while (!terminated) {
            String[] commands = Duke.getUserCommand(sc);

            switch (commands[0].toLowerCase()) {
                case "bye":
                    Duke.printMessage(EXIT_MESSAGE);
                    terminated = true;
                    break;
                case "list":
                    Duke.printMessage(Duke.listTasks(tasks));
                    break;
                case "mark":
                    int markTaskIndex = Integer.parseInt(commands[1]) - 1;
                    if (markTaskIndex < 0 || markTaskIndex >= tasks.size()) {
                        Duke.printMessage(INDEX_OUT_OF_BOUNDS_MESSAGE);
                        break;
                    }
                    markTask(tasks, markTaskIndex, true);
                    Duke.printMessage("Nice! I've marked this task as done:\n\t" + tasks.get(markTaskIndex));
                    break;
                case "unmark":
                    int unmarkTaskIndex = Integer.parseInt(commands[1]) - 1;
                    if (unmarkTaskIndex < 0 || unmarkTaskIndex >= tasks.size()) {
                        Duke.printMessage(INDEX_OUT_OF_BOUNDS_MESSAGE);
                        break;
                    }
                    markTask(tasks, unmarkTaskIndex, false);
                    Duke.printMessage("Okay, I've marked this task as not done yet:\n\t" + tasks.get(unmarkTaskIndex));
                    break;
                default:
                    String taskDescription = String.join(" ", commands);
                    Task task = new Task(taskDescription);
                    tasks.add(task);
                    Duke.printMessage("Added task: " + taskDescription);
            }
        }
    }

    public static void main(String[] args) {
        Duke.run();
    }
}
