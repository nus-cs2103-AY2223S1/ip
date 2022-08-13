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
            boolean isTerminal = duke.executeCommand(arguments);
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
                taskList.getTask(index).markAsDone();
                break;
            }
            case "unmark": {
                int index = Integer.parseInt(arguments[1]) - 1; // task indexing is 1-indexed
                taskList.getTask(index).markAsUndone();
                break;
            }
            case "list": {
                System.out.println(this.taskList);
                break;
            }
            case "bye": {
                System.out.println("Bye! Hope to see you again soon!");
                return true;
            }
            default: {
                taskList.addTask(new Task(command));
                System.out.println("added: " + command);
                break;
            }
        }
        return false;
    }
}
