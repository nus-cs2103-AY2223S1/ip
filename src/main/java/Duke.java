import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {

    // ChatBot constants
    public static final String NAME = "Duke";
    // UI constants
    public static final String INDENT_CHAR = "\t";
    public static final String LINE_STR = "-".repeat(50);

    private static final Task[] taskList = new Task[100]; // Max capacity: 100
    private static int currTaskListIdx = 0;

    /**
     * Utility function to print line to STDOUT
     *
     * @param line   Line to printed to STDOUT
     * @param indent Number of indentation prefix
     */
    private static void printLine(String line, int indent) {
        System.out.printf("%s%s\n", INDENT_CHAR.repeat(indent), line);
    }

    /**
     * Utility function to format Duke's response and display it to STDOUT
     * <p>
     * Note: Defaults to 1 indent prefix for every response
     * </p>
     *
     * @param response A single response
     */
    private static void respond(String response) {
        printLine(LINE_STR, 1);
        printLine(response, 1);
        printLine(LINE_STR, 1);
    }

    /**
     * Utility function to format Duke's responses and display it to STDOUT
     * <p>
     * Note: Defaults to 1 indent prefix for every response
     * </p>
     *
     * @param responses A collection of lines of responses
     */
    private static void respond(List<String> responses) {
        printLine(LINE_STR, 1);
        for (String respLine : responses) {
            printLine(respLine, 1);
        }
        printLine(LINE_STR, 1);
    }

    private static Command parseCommand(String commandToken) {
        try {
            return Command.valueOf(commandToken.toUpperCase());
        } catch (IllegalArgumentException error) {
            // fall back to `add` command
            return Command.ADD;
        }
    }

    private static void handleListCommand() {
        if (currTaskListIdx == 0) {
            respond("There are no items in the list!");
            return;
        }
        List<String> responses = new ArrayList<>();
        for (int idx = 0; idx < currTaskListIdx; idx++) {
            responses.add(String.format("%d. %s", idx + 1, taskList[idx]));
        }
        respond(responses);
    }

    private static void handleMarkCommand(String[] queryTokens, boolean toMark) {
        // Validate query tokens
        boolean validMarkQuery = queryTokens.length == 2;
        if (!validMarkQuery) {
            respond(String.format(
                "[ERROR] Invalid number of parameters passed to `mark` command. Expected: 1 Got: %d",
                queryTokens.length - 1));
            return;
        }
        // Convert mark idx query to int
        String markIdx = queryTokens[1];
        try {
            int taskListIdx = Integer.parseInt(markIdx);
            // Validate taskListIdx
            if (taskListIdx < 0 || taskListIdx > currTaskListIdx) {
                respond("[ERROR] Invalid task selected with `mark` command");
                return;
            }
            Task task = taskList[taskListIdx - 1];
            if (toMark) {
                task.mark();
                respond(Arrays.asList("Nice! I've mark this task as done:",
                    String.format("\t%s", task)));
            } else {
                task.unmark();
                respond(Arrays.asList("OK, I've marked this task as not done yet:",
                    String.format("\t%s", task)));
            }
        } catch (NumberFormatException error) {
            respond("[ERROR] `mark` command expects a number as parameter!");
        }
    }

    public static void main(String[] args) {
        // Greetings
        respond(Arrays.asList(String.format("Hi I'm %s", NAME), "What can I do for you?"));

        // Chat
        boolean terminate = false;
        Scanner input = new Scanner(System.in);
        while (!terminate && input.hasNextLine()) {
            String query = input.nextLine();
            String[] queryTokens = query.split(" ");

            String commandToken = queryTokens[0];
            Command command = parseCommand(commandToken);
            switch (command) {
                case BYE:
                    // terminate
                    terminate = true;
                    respond("Bye. Hope to see you again soon!");
                    break;
                case LIST:
                    handleListCommand();
                    break;
                case MARK:
                    handleMarkCommand(queryTokens, true);
                    break;
                case UNMARK:
                    handleMarkCommand(queryTokens, false);
                    break;
                case ADD:
                    // add to taskList
                    taskList[currTaskListIdx] = new Task(query);
                    currTaskListIdx++;
                    respond(String.format("added: %s", query));
            }
        }
    }
}
