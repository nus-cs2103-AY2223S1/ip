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
            throw new CommandException(String.join("\n",
                "Invalid `list` command format!",
                "Expected format: list"));
        }
    }

    @Override
    public String run(TaskList taskList) {
        if (taskList.isEmpty()) {
            return "There are no items in the task list!";
        }

        List<String> responseList = new ArrayList<>();
        for (int taskIdx = 0; taskIdx < taskList.size(); taskIdx++) {
            responseList.add(String.format("%d. %s", taskIdx + 1, taskList.getTask(taskIdx)));
        }
        return String.join("\n", responseList);
    }
}
