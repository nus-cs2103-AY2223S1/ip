package command;

import data.TaskList;
import data.tasks.Task;

import java.util.regex.Pattern;
import java.util.regex.MatchResult;

import util.CommandUtils;

public class CommandDeleteHandler extends CommandHandler {

    private static final Pattern commandRegexPattern = Pattern.compile("^delete (\\d+)");

    CommandDeleteHandler(String commandStr) throws CommandException {
        super(commandStr, commandRegexPattern);
        if (!isCommandValid()) {
            throw new CommandException(String.join("\n",
                "Invalid `delete` command format!",
                "Expected format: delete <task-number>",
                "Examples:",
                "\t- delete 1"
            ));
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
                return new CommandResponse(
                    CommandUtils.generateDeleteTaskResponse(deletedTask, taskList.size()), true);
            }
        } catch (NumberFormatException error) {
            throw new CommandException(String.join("\n",
                "Task number should be a number!",
                "Got: %s", taskIdxStr
            ));
        }
    }
}
