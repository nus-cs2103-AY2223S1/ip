import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Duke {
    private final TaskList taskList = new TaskList();

    public static void main(String[] args) {
        String logo = " ____        _        \n" +
                "|  _ \\ _   _| | _____ \n" +
                "| | | | | | | |/ / _ \\\n" +
                "| |_| | |_| |   <  __/\n" +
                "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke!");
        System.out.println("What can I do for you?\n");

        Scanner scanner = new Scanner(System.in);
        Duke duke = new Duke();

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            boolean isTerminal = duke.executeCommand(input);
            if (isTerminal) {
                return;
            }
        }
    }

    private static String[] getArguments(String command) {
        if (command.isBlank()) {
            return new String[0];
        }
        return command.split(" ");
    }

    private static int findArgumentIndex(String[] arguments, String query) {
        return IntStream.range(0, arguments.length)
                .filter(i -> arguments[i].equals(query))
                .findFirst()
                .orElseThrow();
    }

    private static String concatenateArguments(String[] arguments, int start, int end) {
        return Arrays.stream(arguments, start, end)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    private static String concatenateArguments(String[] arguments, int start) {
        return Arrays.stream(arguments, start, arguments.length)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    private void printTaskListSize() {
        System.out.printf("You now have %d %s.\n", taskList.size(), taskList.size() == 1 ? "task" : "tasks");
    }

    /**
     * Executes a given command assuming all arguments are correct.
     *
     * @param command the command string
     * @return `true` if the command is terminal, `false` otherwise
     */
    private boolean executeCommand(String command) {
        String[] arguments = getArguments(command);
        if (arguments.length == 0) {
            System.out.println("Please enter a command.");
            return false;
        }
        switch (arguments[0]) {
            case "mark": {
                int index = Integer.parseInt(arguments[1]) - 1; // task indexing is 1-indexed
                Task task = taskList.getTask(index);
                task.markAsDone();
                System.out.println("I've marked this task as done.");
                System.out.println(task);
                break;
            }
            case "unmark": {
                int index = Integer.parseInt(arguments[1]) - 1; // task indexing is 1-indexed
                Task task = taskList.getTask(index);
                task.markAsUndone();
                System.out.println("I've unmarked this task as done.");
                System.out.println(task);
                break;
            }
            case "list": {
                System.out.println("Here are your tasks:");
                System.out.println(this.taskList);
                break;
            }
            case "bye": {
                System.out.println("Bye! Hope to see you again soon!");
                return true;
            }
            case "todo": {
                Task task = new Todo(command);
                taskList.addTask(task);
                System.out.println("I've added this task.");
                System.out.println(task);
                printTaskListSize();
                break;
            }
            case "deadline": {
                // Find the "/by" delimiter to get the two arguments.
                int delimiter = findArgumentIndex(arguments, "/by");
                String description = concatenateArguments(arguments, 1, delimiter);
                String deadline = concatenateArguments(arguments, delimiter + 1);
                // Create and add the task.
                Task task = new Deadline(description, deadline);
                taskList.addTask(task);
                System.out.println("I've added this deadline.");
                System.out.println(task);
                printTaskListSize();
                break;
            }
            case "event": {
                // Find the "/at" delimiter to get the two arguments.
                int delimiter = findArgumentIndex(arguments, "/at");
                String description = concatenateArguments(arguments, 1, delimiter);
                String datetime = concatenateArguments(arguments, delimiter + 1);
                // Create and add the task.
                Task task = new Event(description, datetime);
                taskList.addTask(task);
                System.out.println("I've added this event.");
                System.out.println(task);
                printTaskListSize();
                break;
            }
            default: {
                System.out.println("Unknown command.");
            }
        }
        System.out.println();
        return false;
    }
}
