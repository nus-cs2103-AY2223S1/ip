package duke.command.handler;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import duke.command.CommandException;
import duke.command.response.AddTaskResponse;
import duke.command.response.CommandResponse;
import duke.data.TaskList;
import duke.data.tasks.TaskDeadline;

public class CommandDeadlineHandler extends CommandHandler {

    protected static final String INVALID_FORMAT_MSG = String.join("\n",
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
        return INVALID_FORMAT_MSG;
    }

    /**
     * Add a deadline event to the task list
     *
     * @param taskList task list
     * @return add task response
     * @throws CommandException if date-time for event task cannot be parsed
     */
    @Override
    public CommandResponse run(TaskList taskList) throws CommandException {
        MatchResult regexMatchResult = commandRegexMatcher.toMatchResult();

        String deadlineTaskTitle = regexMatchResult.group(1);
        String deadlineDateTimeStr = regexMatchResult.group(2);

        TaskDeadline deadlineTask = new TaskDeadline(deadlineTaskTitle,
            parseDateTime(deadlineDateTimeStr));
        taskList.addTask(deadlineTask);

        return new AddTaskResponse(deadlineTask, taskList.size());
    }
}
