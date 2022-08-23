package command;

import data.TaskList;
import data.tasks.Task;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import util.CommandUtils;

public class CommandDeleteHandler extends CommandHandler {

    private static final Pattern commandRegexPattern = Pattern.compile("^delete (\\d+)");

    CommandDeleteHandler(String commandStr) throws CommandException {
        super(commandStr, commandRegexPattern);
        if (!isCommandValid()) {
            throw new CommandException(
                "`delete` command expects a single number argument (e.g. `delete 1`)");
        }
    }

    @Override
    public CommandResponse run(TaskList taskList) throws CommandException {
        MatchResult regexMatchResult = commandRegexMatcher.toMatchResult();
        String taskIdxStr = regexMatchResult.group(1);
        try {
            int taskIdx = Integer.parseInt(taskIdxStr);
            if (taskIdx <= 0 || taskIdx > taskList.size()) {
                throw new CommandException("Invalid task selected!");
            } else {
                Task deletedTask = taskList.deleteTask(taskIdx - 1);
                return CommandUtils.generateDeleteTaskResponse(deletedTask, taskList.size());
            }
        } catch (NumberFormatException error) {
            throw new CommandException(
                String.format("`delete` expects a number argument. Got: %s", taskIdxStr));
        }
    }
}
