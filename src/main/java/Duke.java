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

        switch (inputs[0]) {
            // List out all tasks
            case ("list"): {
                listCommand();
                break;
            }
            // Add todo task
            case ("todo"): {
                addTodoCommand(inputs[1]);
                break;
            }
            // Add deadline task
            case ("deadline"): {
                addDeadlineCommand(inputs[1]);
                break;
            }
            // Add event task
            case ("event"): {
                addEventCommand(inputs[1]);
                break;
            }
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
                printTextWithDivider("Sorry, I don't understand this command.\n" +
                        "Please enter again!\n");
            }
        }
    }

    private static void listCommand() {
        StringBuilder str = new StringBuilder();
        str.append("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            // Display task as 1-index
            str.append(i + 1).append(".").append(taskList.get(i)).append("\n");
        }
        printTextWithDivider(str.toString());
    }

    private static void addTodoCommand(String description) {
        addTask(new Todo(description));
    }

    private static void addDeadlineCommand(String description) {
        String[] deadlineInputs = description.split("/by", 2);
        addTask(new Deadline(deadlineInputs[0], deadlineInputs[1]));
    }

    private static void addEventCommand(String description) {
        String[] eventInputs = description.split("/at", 2);
        addTask(new Event(eventInputs[0], eventInputs[1]));
    }

    private static void markTaskCommand(String taskIndexStr) {
        // Tasks are stored as 0-index but display as 1-index
        // Minus 1 to get the correct task in the taskList
        int taskIndex = Integer.parseInt(taskIndexStr) - 1;
        Task task = taskList.get(taskIndex);
        task.markAsDone();

        String str = "Nice! I've marked this as done:\n" +
                task + "\n";
        printTextWithDivider(str);
    }

    private static void unmarkTaskCommand(String taskIndexStr) {
        // Tasks are stored as 0-index but display as 1-index
        // Minus 1 to get the correct task in the taskList
        int taskIndex = Integer.parseInt(taskIndexStr) - 1;
        Task task = taskList.get(taskIndex);
        task.maskUndone();

        String str = "Ok, I've marked this task as not done yet:\n" +
                task + "\n";
        printTextWithDivider(str);
    }

    private static void addTask(Task task) {
        taskList.add(task);

        String addTaskMessage =  "Got it. I've added this task:\n" +
                "  " + task + "\n" +
                "Now you have " + taskList.size() + " task(s) in the list.\n";

        printTextWithDivider(addTaskMessage);
    }
}
