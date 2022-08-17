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
                // FIXME: Error handling if there is no task index provided or the task index provided is not an integer
                Task doneTask = taskManager.get(Integer.parseInt(input[1]));
                doneTask.markAsDone();
                DukePrinter.print(String.format("%s\n\t%s", Duke.MARK_TASK_AS_DONE_MESSAGE, doneTask));
            } else if (command.equals(Command.UNMARK.toString())) {
                // Retrieve the task index (1-indexed) to mark the task as undone
                // FIXME: Error handling if there is no task index provided or the task index provided is not an integer
                Task undoneTask = taskManager.get(Integer.parseInt(input[1]));
                undoneTask.markAsUndone();
                DukePrinter.print(String.format("%s\n\t%s", Duke.MARK_TASK_AS_UNDONE_MESSAGE, undoneTask));
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
