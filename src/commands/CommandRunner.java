package commands;

import input.Input;
import models.task.TaskModel;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to setup commands with their dependencies and expose method for interaction
 */
public class CommandRunner {
    /**
     * Stores mapping from command name (String) to corresponding Command object
     */
    private Map<String,Command> commandMap;

    public CommandRunner() {
        TaskModel taskModel = new TaskModel();

        // Add new commands here
        Command list = new ListCommand(taskModel);
        Command add = new AddCommand(taskModel);
        Command mark = new MarkCommand(taskModel);
        Command exit = new ExitCommand();

        Command[] commands = new Command[] { list, add, mark, exit };
        commandMap = new HashMap<>();

        for (int i = 0; i < commands.length; i++) {
            Command cmd = commands[i];
            commandMap.put(cmd.commandName, cmd);
        }
    }

    public CommandResponse run(Input input) throws IllegalArgumentException {
        String cmdInput = input.getCommandName();
        if (!commandMap.containsKey(cmdInput)) {
            throw new IllegalArgumentException(String.format("Oh no! I do not recognise the command '%s'.", cmdInput));
        }

        return commandMap.get(cmdInput).run(input);
    }
}
