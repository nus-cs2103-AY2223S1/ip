package duke.command.handler;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import duke.command.CommandException;
import duke.command.response.AddTaskResponse;
import duke.command.response.CommandResponse;
import duke.data.TaskList;
import duke.data.tasks.TaskEvent;

public class CommandEventHandler extends CommandHandler {

    protected static final String INVALID_FORMAT_MSG = String.join("\n",
        "Invalid `event` command format!",
        "Expected format: event <title> /at <YYYY-mm-dd HH:mm>",
        "Examples:",
        "\t- event e1 /at 2022-01-01",
        "\t- event e1 /at 2022-01-01 18:00"
    );
    private static final Pattern commandRegexPattern = Pattern.compile(
        String.format("^event (.+) /at %s", COMMAND_DATETIME_REGEX_STRING));

    public CommandEventHandler(String commandStr) throws CommandException {
        super(commandStr, commandRegexPattern);
    }

    @Override
    protected String getInvalidFormatMessage() {
        return INVALID_FORMAT_MSG;
    }

    /**
     * Add an event task to the task list
     *
     * @param taskList task list
     * @return add task response
     * @throws CommandException if date-time for event task cannot be parsed
     */
    @Override
    public CommandResponse run(TaskList taskList) throws CommandException {
        MatchResult regexMatchResult = commandRegexMatcher.toMatchResult();

        String eventTitle = regexMatchResult.group(1);
        String eventDateTimeStr = regexMatchResult.group(2);

        TaskEvent eventTask = new TaskEvent(eventTitle, parseDateTime(eventDateTimeStr));
        taskList.addTask(eventTask);

        return new AddTaskResponse(eventTask, taskList.size());
    }
}
