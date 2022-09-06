package duke.command.handler;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import duke.command.CommandException;
import duke.command.response.CommandResponse;
import duke.command.response.UpdateTaskResponse;
import duke.command.response.UpdateTaskResponse.UpdateType;
import duke.data.TaskList;
import duke.data.tasks.Task;

public class CommandTagHandler extends CommandHandler {

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
    public CommandResponse run(TaskList taskList) throws CommandException {
        MatchResult regexMatchResult = commandRegexMatcher.toMatchResult();

        String tagName = regexMatchResult.group(1);
        String taskIdxStr = regexMatchResult.group(2);

        try {
            int taskIdx = Integer.parseInt(taskIdxStr);
            if (taskIdx <= 0 || taskIdx > taskList.size()) {
                throw new CommandException("Invalid task selected!");
            }
            Task task = taskList.getTask(taskIdx - 1);
            task.addTag(tagName);
            return new UpdateTaskResponse(task, UpdateType.TAG);
        } catch (NumberFormatException error) {
            throw new CommandException(String.join("\n",
                "Task number should be a number!",
                "Got: %s", taskIdxStr));
        }
    }
}
