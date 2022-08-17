import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private final static List<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        final String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        final String GREETING = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        final String GOODBYE = "Bye. Hope to see you again soon!\n";

        System.out.println("Hello from\n" + LOGO);

        printTextWithDivider(GREETING);

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String command = sc.nextLine();

            // Quit when user enters "bye"
            if (command.equals("bye")) {
                printTextWithDivider(GOODBYE);
                break;
            }

            // Execute the command
            executeCommand(command);
        }

    }

    /**
     * Prints out the given text with dividers to the console
     *
     * @param text The specified text to be printed to the console
     */
    public static void printTextWithDivider(String text) {
        final String divider = "-".repeat(80) + "\n";
        System.out.println(divider + text + divider);
    }

    /**
     * Execute the command entered by user
     *
     * @param command The specified command
     */
    private static void executeCommand(String command) {
        // Limit the words to 2
        String[] inputs = command.split(" ", 2);

        if (inputs.length == 1) {
            switch (inputs[0]) {
                // List out all abilities
                case ("list"): {
                    listCommand();
                    break;
                }
                // Add task to taskList
                default: {
                    addTaskCommand(command);
                }
            }
        } else {
            switch (inputs[0]) {
                // Mark the task as done
                case ("mark"): {
                    markTaskCommand(inputs[1]);
                    break;
                }
                // Mark the task as undone
                case ("unmark"): {
                    unmarkTaskCommand(inputs[1]);
                    break;
                }
                default: {
                    addTaskCommand(command);
                }
            }
        }
    }

    private static void listCommand() {
        StringBuilder str = new StringBuilder();
        str.append("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            str.append(i + 1 + "." + taskList.get(i) + "\n");
        }
        printTextWithDivider(str.toString());
    }

    private static void addTaskCommand(String description) {
        Task task = new Task(description);
        taskList.add(task);
        String addTaskMessage = "added: " + description + "\n";
        printTextWithDivider(addTaskMessage);
    }

    private static void markTaskCommand(String taskIndexStr) {
        // Tasks are stored as 0-indexed but display as 1-index
        int taskIndex = Integer.parseInt(taskIndexStr) - 1;
        Task task = taskList.get(taskIndex);
        task.markAsDone();

        StringBuilder str = new StringBuilder();
        str.append("Nice! I've marked this as done:\n");
        str.append(task + "\n");
        printTextWithDivider(str.toString());
    }

    private static void unmarkTaskCommand(String taskIndexStr) {
        int taskIndex = Integer.parseInt(taskIndexStr) - 1;
        Task task = taskList.get(taskIndex);
        task.maskUndone();

        StringBuilder str = new StringBuilder();
        str.append("Ok, I've marked this task as not done yet:\n");
        str.append(task + "\n");
        printTextWithDivider(str.toString());
    }
}
