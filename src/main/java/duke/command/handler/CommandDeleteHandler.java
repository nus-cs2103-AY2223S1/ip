package duke.command.handler;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import duke.command.CommandException;
import duke.command.handler.base.CommandUpdateTaskHandler;
import duke.command.response.CommandResponse;
import duke.command.response.DeleteTaskResponse;
import duke.data.TaskList;
import duke.data.tasks.Task;

public class CommandDeleteHandler extends CommandUpdateTaskHandler {

    protected static final String INVALID_FORMAT_MESSAGE = String.join("\n",
        "Invalid `delete` command format!",
        "Expected format: delete <task-number>",
        "Examples:",
        "\t- delete 1"
    );
    private static final Pattern commandRegexPattern = Pattern.compile("^delete (\\d+)");

    public CommandDeleteHandler(String commandStr) throws CommandException {
        super(commandStr, commandRegexPattern);
    }

    @Override
    protected String getInvalidFormatMessage() {
        return INVALID_FORMAT_MESSAGE;
    }

    @Override
    protected String getSelectedTaskIdStr() {
        MatchResult regexMatchResult = commandRegexMatcher.toMatchResult();
        return regexMatchResult.group(1);
    }

    @Override
    protected CommandResponse updateTask(Task task, int taskIdx, TaskList taskList) {
        taskList.deleteTask(taskIdx);
        return new DeleteTaskResponse(task, taskList.size());
    }
}
