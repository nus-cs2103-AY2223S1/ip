package duke.command;

import duke.exception.DukeException;

/**
 * Parsers user inputs of the duke program.
 */
public class Parser {
    /** Error message for unknown commands */
    private static final String UNKNOWN_COMMAND_ERROR_MESSAGE = "hmmm..."
        + " bobo doesn't understand what that means (・へ・；)?";
    /** Error message for inputted numbers that could not be parsed to integers */
    private static final String INVALID_INTEGER_ERROR_MESSAGE = "yikes! bobo can't handle this number...";

    /**
     * Parses user input, identifying the command to run and returns that command.
     *
     * @param userInput The command string the user has submitted.
     * @return A command based on the user's input.
     * @throws DukeException If the user input is invalid and a command can't be instantiated.
     */
    public static Command parse(String userInput) throws DukeException {
        if (userInput.equals("list")) {
            return new ListTasksCommand();
        } else if (userInput.matches("mark \\d+")) {
            int taskNumber = parseInt(userInput.substring(5));
            return new MarkTaskDoneCommand(taskNumber);
        } else if (userInput.matches("unmark \\d+")) {
            int taskNumber = parseInt(userInput.substring(7));
            return new MarkTaskNotDoneCommand(taskNumber);
        } else if (userInput.matches("delete \\d+")) {
            int taskNumber = parseInt(userInput.substring(7));
            return new DeleteTaskCommand(taskNumber);
        } else if (userInput.matches("edit \\d+ .+")) {
            return new EditTaskCommand(userInput.substring(5));
        } else if (userInput.matches("find .+")) {
            return new FindTaskCommand(userInput.substring(5));
        } else if (userInput.matches("(?i)schedule.*")) {
            return new ViewScheduleCommand(userInput.substring(8));
        } else if (userInput.matches("(?i)stats.*")) {
            return StatsCommand.getStatsCommand(userInput.substring(5));
        } else if (userInput.matches("((?i)^(todo)(.*))|((?i)^(deadline)(.*))|((?i)^(event)(.*))")) {
            return new AddTaskCommand(userInput);
        } else if (userInput.equals("bye")) {
            return new ExitCommand();
        } else {
            throw new DukeException(UNKNOWN_COMMAND_ERROR_MESSAGE);
        }
    }

    /** Guards against integer overflow */
    private static int parseInt(String taskNumber) throws DukeException {
        try {
            return Integer.parseInt(taskNumber);
        } catch (NumberFormatException e) {
            throw new DukeException(INVALID_INTEGER_ERROR_MESSAGE);
        }
    }
}
