package duke.command.handler;

import java.util.regex.Pattern;

import duke.command.CommandException;
import duke.command.response.CommandResponse;
import duke.data.TaskList;

public class CommandListHandler extends CommandHandler {

    protected static final String INVALID_FORMAT_MSG = String.join("\n",
        "Invalid `list` command format!",
        "Expected format: list"
    );
    private static final Pattern commandRegexPattern = Pattern.compile("^list$");

    public CommandListHandler(String commandStr) throws CommandException {
        super(commandStr, commandRegexPattern);
    }

    @Override
    protected String getInvalidFormatMessage() {
        return INVALID_FORMAT_MSG;
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
        if (taskList.isEmpty()) {
            return new CommandResponse("There are no items in the task list!", false, false);
        }

        return new CommandResponse(taskList.toString(), false, false);
    }
}
