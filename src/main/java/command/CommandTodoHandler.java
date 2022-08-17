package command;

import data.TaskList;
import data.tasks.TaskTodo;
import java.util.List;
import util.CommandUtils;

public class CommandTodoHandler extends CommandHandler {

    CommandTodoHandler(TaskList taskList) {
        super(taskList);
    }

    @Override
    public boolean validateCommand(List<String> commandTokens) {
        // Ensure there is a description after `todo` command
        return commandTokens.size() > 1;
    }

    @Override
    public List<String> run(List<String> commandTokens) throws CommandException {
        if (!validateCommand(commandTokens)) {
            throw new CommandException("The `todo` command expects a description!");
        }

        TaskTodo todoTask = new TaskTodo(
            gatherCommandTokens(commandTokens, 1, commandTokens.size(), " "));
        taskList.addTask(todoTask);

        return CommandUtils.generateAddTaskResponse(todoTask, taskList.size());
    }
}
