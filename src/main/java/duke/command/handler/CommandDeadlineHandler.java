package duke.command.handler;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import duke.command.CommandException;
import duke.command.handler.base.CommandAddTaskHandler;
import duke.data.tasks.Task;
import duke.data.tasks.TaskDeadline;

public class CommandDeadlineHandler extends CommandAddTaskHandler {

    protected static final String INVALID_FORMAT_MESSAGE = String.join("\n",
        "Invalid `deadline` command format!",
        "Expected format: deadline <title> /by <YYYY-mm-dd HH:mm>",
        "Examples:",
        "\t- deadline d1 /by 2022-01-01",
        "\t- deadline d1 /by 2022-01-01 18:00"
    );
    private static final Pattern commandRegexPattern = Pattern.compile(
        String.format("^deadline (.+) /by %s", COMMAND_DATETIME_REGEX_STRING));

    public CommandDeadlineHandler(String commandStr) throws CommandException {
        super(commandStr, commandRegexPattern);
    }

    @Override
    protected String getInvalidFormatMessage() {
        return INVALID_FORMAT_MESSAGE;
    }

    /**
     * Add a deadline event to the task list
     *
     * @param taskList task list
     * @return add task response
     * @throws CommandException if date-time for event task cannot be parsed
     */
    @Override
    protected Task getTaskFromCommand() throws CommandException {
        MatchResult regexMatchResult = commandRegexMatcher.toMatchResult();

        String deadlineTaskTitle = regexMatchResult.group(1);
        String deadlineDateTimeStr = regexMatchResult.group(2);

        return new TaskDeadline(deadlineTaskTitle, parseDateTime(deadlineDateTimeStr));
    }
}
