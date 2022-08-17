package models;

import exceptions.DukeException;
import handlers.*;

import java.util.HashMap;

public class CommandManager {
    public final HashMap<CommandType, DukeCommand> commands;

    public CommandManager() {
        // Ensure that all commands have a corresponding command initializer
        CommandType[] allCommands = CommandType.values();
        this.commands = new HashMap<>();

        for (CommandType command : allCommands) {
            switch (command) {
                // List all tasks in the task manager
                case LIST:
                    this.commands.put(command, new ListTasksCommand());
                    break;

                // Modifying operations on a task
                case MARK:
                    this.commands.put(command, new MarkTaskCommand());
                    break;
                case UNMARK:
                    this.commands.put(command, new UnmarkTaskCommand());
                    break;
                case DELETE:
                    this.commands.put(command, new DeleteTaskCommand());
                    break;

                // Creating operations on a task
                case TODO:
                    this.commands.put(command, new AddToDoTaskCommand());
                    break;
                case DEADLINE:
                    this.commands.put(command, new AddDeadlineTaskCommand());
                    break;
                case EVENT:
                    this.commands.put(command, new AddEventTaskCommand());
                    break;

                // Termination of program
                case BYE:
                    this.commands.put(command, new ByeCommand());
                    break;
            }
        }
    }

    /**
     * Retrieve the command corresponding to the specified key
     *
     * @param key Command key entered by the user
     * @return Corresponding DukeCommand if the key is valid
     */
    public DukeCommand get(CommandType key) {
        return this.commands.get(key);
    }

    public boolean isTerminatingCommand(CommandType key) {
        return key.equals(CommandType.BYE);
    }
}
