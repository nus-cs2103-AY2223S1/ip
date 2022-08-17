package command;

import data.TaskList;
import data.tasks.Task;
import java.util.ArrayList;
import java.util.List;
import util.CommandUtils;

public class CommandDeleteHandler extends CommandHandler {

    CommandDeleteHandler(TaskList taskList) {
        super(taskList);
    }

    @Override
    public boolean validateCommand(List<String> commandTokens) {
        // There must be a task index followed by the `delete` command
        return commandTokens.size() == 2;
    }

    @Override
    public List<String> run(List<String> commandTokens) throws CommandException {
        if (!validateCommand(commandTokens)) {
            throw new CommandException("The `delete` command only expects 1 parameter!");
        }

        List<String> responseList = new ArrayList<>();

        String taskIdxStr = commandTokens.get(1);
        try {
            int taskIdx = Integer.parseInt(taskIdxStr);
            if (taskIdx <= 0 || taskIdx > taskList.size()) {
                throw new CommandException("Invalid task selected!");
            } else {
                Task deletedTask = taskList.deleteTask(taskIdx - 1);
                responseList.addAll(CommandUtils.generateDeleteTaskResponse(deletedTask,
                    taskList.size()));
            }
        } catch (NumberFormatException error) {
            throw new CommandException("Invalid task selected!");
        }

        return responseList;
    }
}
