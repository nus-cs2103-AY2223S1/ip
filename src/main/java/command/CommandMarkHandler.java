package command;

import command.UpdateTaskResponse.UpdateType;

import data.TaskList;
import data.tasks.Task;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class CommandMarkHandler extends CommandHandler {

    private static final Pattern commandRegexPattern = Pattern.compile("^(u?n?mark) (\\d+)");

    CommandMarkHandler(String commandStr) throws CommandException {
        super(commandStr, commandRegexPattern);
        if (!isCommandValid()) {
            throw new CommandException(String.join("\n",
                "Invalid `mark`/`unmark` command format!",
                "Expected format: mark <task-number> / unmark <task-number>",
                "Examples:",
                "\t- mark 1",
                "\t- unmark 1"
            ));
        }
    }

    @Override
    public CommandResponse run(TaskList taskList) throws CommandException {
        MatchResult regexMatchResult = commandRegexMatcher.toMatchResult();

        boolean toMark = regexMatchResult.group(1).equals("mark");
        String taskIdxStr = regexMatchResult.group(2);
        try {
            int taskIdx = Integer.parseInt(taskIdxStr);
            if (taskIdx <= 0 || taskIdx > taskList.size()) {
                throw new CommandException("Invalid task selected!");
            }
            Task task = taskList.getTask(taskIdx - 1);
            if (toMark) {
                task.mark();
            } else {
                task.unmark();
            }
            return new UpdateTaskResponse(task, toMark ? UpdateType.MARK : UpdateType.UNMARK);
        } catch (NumberFormatException error) {
            throw new CommandException(String.join("\n",
                "Task number should be a number!",
                "Got: %s", taskIdxStr));
        }
    }
}
