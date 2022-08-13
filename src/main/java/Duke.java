import java.util.Scanner;

public class Duke {
    private final TaskList taskList = new TaskList();

    public static void main(String[] args) {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke!");
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        Duke duke = new Duke();

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            String[] arguments = getArguments(input);

            if (arguments.length == 0) {
                System.out.println("Please enter a command.");
                continue;
            }
            System.out.println();
            boolean isTerminal = duke.executeCommand(arguments);
            System.out.println();
            if (isTerminal) {
                return;
            }
        }
    }

    private static String[] getArguments(String command) {
        return command.split(" ");
    }

    /**
     * Executes a given command assuming all arguments are correct.
     *
     * @param arguments the arguments of the command
     * @return `true` if the command is terminal, `false` otherwise
     */
    private boolean executeCommand(String[] arguments) {
        String command = arguments[0];
        switch (command) {
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
            default: {
                Task task = new Task(command);
                taskList.addTask(task);
                System.out.println("I've added this task.");
                System.out.println(task);
                break;
            }
        }
        return false;
    }
}
