package command;

import data.TaskList;
import data.tasks.TaskEvent;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import util.CommandUtils;

public class CommandEventHandler extends CommandHandler {

    private static final Pattern commandRegexPattern = Pattern.compile(
        String.format("^event (.+) /at %s", commandDateTimeRegexStr));

    CommandEventHandler(String commandStr) throws CommandException {
        super(commandStr, commandRegexPattern);
        if (!isCommandValid()) {
            throw new CommandException(String.join("\n",
                "Invalid `event` command format!",
                "Expected format: event <title> /at <YYYY-mm-dd HH:mm>",
                "Examples:",
                "\t- event e1 /at 2022-01-01",
                "\t- event e1 /at 2022-01-01 18:00")
            );
        }
    }

    @Override
    public CommandResponse run(TaskList taskList) throws CommandException {
        MatchResult regexMatchResult = commandRegexMatcher.toMatchResult();

        String eventTitle = regexMatchResult.group(1);
        String eventDateTimeStr = regexMatchResult.group(2);

        TaskEvent eventTask = new TaskEvent(eventTitle, parseDateTime(eventDateTimeStr));
        taskList.addTask(eventTask);

        return new CommandResponse(CommandUtils.generateAddTaskResponse(eventTask, taskList.size()),
            true);
    }
}
