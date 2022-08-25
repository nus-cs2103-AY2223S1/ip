package duke.command.handler;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public CommandResponse run(TaskList taskList) {
        if (taskList.isEmpty()) {
            return new CommandResponse("There are no items in the task list!", false, false);
        }

        List<String> responseList = new ArrayList<>();
        for (int taskIdx = 0; taskIdx < taskList.size(); taskIdx++) {
            responseList.add(String.format("%d. %s", taskIdx + 1, taskList.getTask(taskIdx)));
        }
        return new CommandResponse(String.join("\n", responseList), false, false);
    }
}
