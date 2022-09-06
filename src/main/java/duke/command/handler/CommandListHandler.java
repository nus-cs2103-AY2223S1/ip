package duke.command.handler;

import java.util.regex.Pattern;

import duke.command.CommandException;
import duke.command.handler.base.CommandHandler;
import duke.command.response.CommandResponse;
import duke.data.TaskList;

public class CommandListHandler extends CommandHandler {

    protected static final String INVALID_FORMAT_MESSAGE = String.join("\n",
        "Invalid `list` command format!",
        "Expected format: list [tag]",
        "Examples:",
        "\t- list",
        "\t- list #CS2103"
    );
    private static final Pattern commandRegexPattern = Pattern.compile("(^list$)|(^list #(\\w+)$)");

    public CommandListHandler(String commandStr) throws CommandException {
        super(commandStr, commandRegexPattern);
    }

    @Override
    protected String getInvalidFormatMessage() {
        return INVALID_FORMAT_MESSAGE;
    }

    /**
     * Returns a CommandResponse with a string representation of all tasks in the task list
     * <p>
     * If there is no tasks in the task list, return a string to inform user
     * </p>
     *
     * @param taskList task list
     * @return command response
     */
    @Override
    public CommandResponse run(TaskList taskList) {
        String queryTag = commandRegexMatcher.group(3);
        boolean toFilterByTag = queryTag != null;
        TaskList filteredTaskList =
            toFilterByTag ? taskList.filterTasks(task -> task.hasTag(queryTag)) : taskList;

        return new CommandResponse(filteredTaskList.toString(), false, false);
    }
}
