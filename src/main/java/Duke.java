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

    private static void addTaskToList(Task task) {
        taskList[currTaskListIdx] = task;
        currTaskListIdx++;
        respond(Arrays.asList("Got it. I've added this task:", String.format("\t%s", task),
            String.format("Now you have %d tasks in the list", currTaskListIdx)));
    }

    private static Command parseCommand(String commandToken) {
        try {
            return Command.valueOf(commandToken.toUpperCase());
        } catch (IllegalArgumentException error) {
            return Command.UNKNOWN;
        }
    }

    private static String gatherStringTokens(List<String> strTokenList, int start, int end) {
        StringBuilder strBuilder = new StringBuilder();
        for (int idx = start; idx < end; idx++) {
            strBuilder.append(strTokenList.get(idx));
            if (idx < end - 1) {
                strBuilder.append(' ');
            }
        }

        return strBuilder.toString();
    }

    private static boolean isValidAddTaskCommand(List<String> queryTokens, String dataMarker) {
        if (dataMarker.equals("")) {
            // No data marker
            return queryTokens.size() > 1;
        }
        // Validate add task with data marker (deadline, event)
        // 1. Contains the data marker
        // 2. There is a data specified after the data marker
        // 3. Data marker is not right after command as it implies no task description
        int dataMarkerIdx = queryTokens.indexOf(dataMarker);
        return queryTokens.size() > 1 && dataMarkerIdx != -1
            && dataMarkerIdx != queryTokens.size() - 1 && dataMarkerIdx != 1;
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

    private static void handleMarkCommand(List<String> queryTokens, boolean toMark) {
        boolean isValidMarkQuery = queryTokens.size() == 2;
        if (!isValidMarkQuery) {
            respond(String.format(
                "[ERROR] Invalid number of parameters passed to `mark` command. Expected: 1 Got: %d",
                queryTokens.size() - 1));
            return;
        }
        // Convert mark idx query to int
        String markIdx = queryTokens.get(1);
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

    private static void handleToDoCommand(List<String> queryTokens) {
        if (!isValidAddTaskCommand(queryTokens, "")) {
            respond("The description of a `todo` cannot be empty!");
            return;
        }
        Task todoTask = new TaskTodo(gatherStringTokens(queryTokens, 1, queryTokens.size()));
        addTaskToList(todoTask);
    }

    private static void handleDeadlineCommand(List<String> queryTokens) {
        if (!isValidAddTaskCommand(queryTokens, TaskDeadline.deadlineMarker)) {
            respond("Invalid `deadline` command!");
            return;
        }
        int deadlineMarkerIdx = queryTokens.indexOf(TaskDeadline.deadlineMarker);
        String deadlineDesc = gatherStringTokens(queryTokens, 1, deadlineMarkerIdx);
        String deadline = gatherStringTokens(queryTokens, deadlineMarkerIdx + 1,
            queryTokens.size());

        Task deadlineTask = new TaskDeadline(deadlineDesc, deadline);
        addTaskToList(deadlineTask);
    }

    private static void handleEventCommand(List<String> queryTokens) {
        if (!isValidAddTaskCommand(queryTokens, TaskEvent.timingMarker)) {
            respond("Invalid `event` command!");
            return;
        }
        int timingMarkerIdx = queryTokens.indexOf(TaskEvent.timingMarker);
        String eventDesc = gatherStringTokens(queryTokens, 1, timingMarkerIdx);
        String timing = gatherStringTokens(queryTokens, timingMarkerIdx + 1,
            queryTokens.size());

        Task eventTask = new TaskEvent(eventDesc, timing);
        addTaskToList(eventTask);
    }

    public static void main(String[] args) {
        // Greetings
        respond(Arrays.asList(String.format("Hi I'm %s", NAME), "What can I do for you?"));

        // Chat
        boolean terminate = false;
        Scanner input = new Scanner(System.in);
        while (!terminate && input.hasNextLine()) {
            String query = input.nextLine();
            List<String> queryTokens = Arrays.asList(query.split(" "));

            String commandToken = queryTokens.get(0);
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
                case TODO:
                    handleToDoCommand(queryTokens);
                    break;
                case DEADLINE:
                    handleDeadlineCommand(queryTokens);
                    break;
                case EVENT:
                    handleEventCommand(queryTokens);
                    break;
                case UNKNOWN:
                    respond("Unknown command");
            }
        }
    }
}
