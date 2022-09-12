package duke.command.handler;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import duke.command.CommandException;
import duke.command.handler.base.CommandUpdateTaskHandler;
import duke.command.response.CommandResponse;
import duke.command.response.UpdateTaskResponse;
import duke.data.TaskList;
import duke.data.tasks.Task;

public class CommandTagHandler extends CommandUpdateTaskHandler {

    protected static final String INVALID_FORMAT_MESSAGE = String.join("\n",
        "Invalid `tag` command format!",
        "Expected format: tag #<tag-name> <task-number>",
        "Examples:",
        "\t- tag #CS2103 1"
    );
    private static final Pattern commandRegexPattern = Pattern.compile("^tag #(\\w+) (\\d+)$");

    public CommandTagHandler(String commandStr) throws CommandException {
        super(commandStr, commandRegexPattern);
    }

    @Override
    protected String getInvalidFormatMessage() {
        return INVALID_FORMAT_MESSAGE;
    }

    @Override
    protected String getSelectedTaskIdStr() {
        MatchResult regexMatchResult = commandRegexMatcher.toMatchResult();
        return regexMatchResult.group(2);
    }

    @Override
    protected CommandResponse updateTask(Task task, int taskIdx, TaskList taskList) {
        MatchResult regexMatchResult = commandRegexMatcher.toMatchResult();
        String tagName = regexMatchResult.group(1);

        task.addTag(tagName);
        return new UpdateTaskResponse(task, UpdateTaskResponse.UpdateType.TAG);
    }
}
