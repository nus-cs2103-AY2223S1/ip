package commandhandler;

import data.TaskList;
import java.util.List;
import java.util.ArrayList;

public class CommandListHandler extends CommandHandler {

    public CommandListHandler(TaskList taskList) {
        super(taskList);
    }

    @Override
    public boolean validateCommand(List<String> commandTokens) {
        // There should only be a `list` command
        return commandTokens.size() == 1;
    }

    @Override
    public List<String> run(List<String> commandTokens) {
        List<String> responseList = new ArrayList<>();

        if (taskList.isEmpty()) {
            responseList.add("There are no items in the list!");
        } else {
            for (int taskIdx = 0; taskIdx < taskList.size(); taskIdx++) {
                responseList.add(String.format("%d. %s", taskIdx + 1, taskList.getTask(taskIdx)));
            }
        }

        return responseList;
    }
}
