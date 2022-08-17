import java.util.Scanner;

/**
 * @author Emily Ong Hui Qi
 */
public class Duke {
    // Name of the chatbot
    private static final String NAME = "Duke";

    // The greeting message used by the chatbot when the program starts
    private static final String GREETING_MESSAGE = String.format("Hello! I'm %s\nWhat can I do for you?", Duke.NAME);
    // The goodbye message used by the chatbot when the program terminates
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String ADD_TASK_MESSAGE = "added:";
    private static final String MARK_TASK_AS_DONE_MESSAGE = "Nice! I've marked this task as done:";
    private static final String MARK_TASK_AS_UNDONE_MESSAGE = "OK, I've marked this task as not done yet:";
    
    private static final String MISSING_TASK_INDEX_ERROR = "Oops! You are missing a task number!\n" +
            "Use the 'list' command to view the tasks and their number.";
    private static final String NAN_TASK_INDEX_ERROR = "Oops! The task number you provided is not a number!";
    private static final String TASK_INDEX_IS_INVALID_ERROR = "Oops! The task number you provided is not valid!\n" +
            "Use the 'list' command to view the tasks and their number.";

    // FIXME: Refactor to use proper enums (A-Enums level)
    private enum Command {
        // The 'bye' command is used to indicate to the program to exit
        BYE,
        // The 'list' command is used to indicate to the program to list the inputted commands
        LIST,
        // The 'mark' command is used together with the task index to mark the specified task as done
        MARK,
        // The 'unmark' command is used together with the task index to mark the specified task as undone
        UNMARK;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }

    private static Task getTask(TaskManager taskManager, int taskNumber) {
        Task task;
        try {
            task = taskManager.get(taskNumber);
            return task;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        // Greet the user
        DukePrinter.print(Duke.GREETING_MESSAGE);

        while (scanner.hasNextLine()) {
            // Receive the command entered by the user
            String[] input = scanner.nextLine().split(" ");
            String command = input[0];

            if (command.equals(Command.BYE.toString())) {
                break;
            }

            if (command.equals(Command.LIST.toString())) {
                DukePrinter.print(taskManager.toString());
            } else if (command.equals(Command.MARK.toString())) {
                // Retrieve the task index (1-indexed) to mark the task as done
                if (input.length == 1) {
                    DukePrinter.print(Duke.MISSING_TASK_INDEX_ERROR);
                    continue;
                }
                int taskNumber;
                try {
                    taskNumber = Integer.parseInt(input[1]);
                } catch (NumberFormatException e) {
                    DukePrinter.print(Duke.NAN_TASK_INDEX_ERROR);
                    continue;
                }
                Task task = Duke.getTask(taskManager, taskNumber);
                if (task != null) {
                    task.markAsDone();
                    DukePrinter.print(String.format("%s\n\t%s", Duke.MARK_TASK_AS_DONE_MESSAGE, task));
                } else {
                    DukePrinter.print(Duke.TASK_INDEX_IS_INVALID_ERROR);
                }
            } else if (command.equals(Command.UNMARK.toString())) {
                // Retrieve the task index (1-indexed) to mark the task as undone
                if (input.length == 1) {
                    DukePrinter.print(Duke.MISSING_TASK_INDEX_ERROR);
                    continue;
                }
                int taskNumber;
                try {
                    taskNumber = Integer.parseInt(input[1]);
                } catch (NumberFormatException e) {
                    DukePrinter.print(Duke.NAN_TASK_INDEX_ERROR);
                    continue;
                }
                Task task = Duke.getTask(taskManager, taskNumber);
                if (task != null) {
                    task.markAsUndone();
                    DukePrinter.print(String.format("%s\n\t%s", Duke.MARK_TASK_AS_UNDONE_MESSAGE, task));
                } else {
                    DukePrinter.print(Duke.TASK_INDEX_IS_INVALID_ERROR);
                }
            } else {
                taskManager.add(command);
                DukePrinter.print(String.format("%s %s", Duke.ADD_TASK_MESSAGE, command));
            }
        }

        scanner.close();

        // Bid the user goodbye
        DukePrinter.print(Duke.GOODBYE_MESSAGE);
    }
}
