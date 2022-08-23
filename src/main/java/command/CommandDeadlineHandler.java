package command;

import data.TaskList;
import data.tasks.TaskDeadline;

import java.util.regex.Pattern;
import java.util.regex.MatchResult;

import util.CommandUtils;

public class CommandDeadlineHandler extends CommandHandler {

    private static final Pattern commandRegexPattern = Pattern.compile(
        String.format("^deadline (.+) /by %s", commandDateTimeRegexStr));

    CommandDeadlineHandler(String commandStr) throws CommandException {
        super(commandStr, commandRegexPattern);
        if (!isCommandValid()) {
            throw new CommandException(String.join("\n",
                "Invalid `deadline` command format!",
                "Expected format: deadline <title> /by <YYYY-mm-dd HH:mm>",
                "Examples:",
                "\t- deadline d1 /at 2022-01-01",
                "\t- deadline d1 /at 2022-01-01 18:00")
            );
        }
    }

    @Override
    public CommandResponse run(TaskList taskList) throws CommandException {
        MatchResult regexMatchResult = commandRegexMatcher.toMatchResult();

        String deadlineTaskTitle = regexMatchResult.group(1);
        String deadlineDateTimeStr = regexMatchResult.group(2);

        TaskDeadline deadlineTask = new TaskDeadline(deadlineTaskTitle,
            parseDateTime(deadlineDateTimeStr));
        taskList.addTask(deadlineTask);

        return new CommandResponse(
            CommandUtils.generateAddTaskResponse(deadlineTask, taskList.size()), true);
    }
}
