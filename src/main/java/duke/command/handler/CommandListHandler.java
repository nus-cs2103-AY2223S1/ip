package duke.command.handler;

import duke.command.CommandException;
import duke.command.response.CommandResponse;

import duke.data.TaskList;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class CommandListHandler extends CommandHandler {

    private static final Pattern commandRegexPattern = Pattern.compile("^list$");

    public CommandListHandler(String commandStr) throws CommandException {
        super(commandStr, commandRegexPattern);
        if (!isCommandValid()) {
            throw new CommandException(String.join("\n",
                "Invalid `list` command format!",
                "Expected format: list"));
        }
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
