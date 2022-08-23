package command;

import data.TaskList;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class CommandListHandler extends CommandHandler {

    private static final Pattern commandRegexPattern = Pattern.compile("^list$");

    public CommandListHandler(String commandStr) throws CommandException {
        super(commandStr, commandRegexPattern);
        if (!isCommandValid()) {
            throw new CommandException("`list` command expects no arguments!");
        }
    }

    @Override
    public CommandResponse run(TaskList taskList) {
        List<String> responseList = new ArrayList<>();

        if (taskList.isEmpty()) {
            responseList.add("There are no items in the list!");
        } else {
            for (int taskIdx = 0; taskIdx < taskList.size(); taskIdx++) {
                responseList.add(String.format("%d. %s", taskIdx + 1, taskList.getTask(taskIdx)));
            }
        }

        return new CommandResponse(responseList, false);
    }
}
