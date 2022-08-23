package command;

import data.TaskList;
import data.tasks.TaskDeadline;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import util.CommandUtils;

public class CommandDeadlineHandler extends CommandHandler {

    private static final Pattern commandRegexPattern = Pattern.compile(
        String.format("^deadline (.+) /by %s", commandDateTimeRegexStr));

    CommandDeadlineHandler(String commandStr) throws CommandException {
        super(commandStr, commandRegexPattern);
        if (!isCommandValid()) {
            throw new CommandException(
                "Invalid `deadline` command format (expected: deadline deadline-title /by datetime)");
        }
    }

    @Override
    public List<String> run(TaskList taskList) throws CommandException {
        MatchResult regexMatchResult = commandRegexMatcher.toMatchResult();

        String deadlineTaskTitle = regexMatchResult.group(1);
        String deadlineDateTimeStr = regexMatchResult.group(2);

        TaskDeadline deadlineTask = new TaskDeadline(deadlineTaskTitle,
            parseDateTime(deadlineDateTimeStr));
        taskList.addTask(deadlineTask);

        return CommandUtils.generateAddTaskResponse(deadlineTask, taskList.size());
    }
}
