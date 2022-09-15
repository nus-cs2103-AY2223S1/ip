package duke.command;

import java.util.HashMap;
import java.util.Map;

import duke.exceptions.CantDeleteDefaultException;
import duke.exceptions.CommandAlreadyExistException;
import duke.exceptions.CommandDoesNotExistException;
import duke.exceptions.DukeException;

/**
 * Command holder class to select a command enum based on txt
 */
public class CommandSelector {
    private Map<String, CommandsEnum> commands = new HashMap<>();

    /**
     * Initialises the selector class, adds base commands into the selector
     */
    public CommandSelector() {
        for (CommandsEnum c : CommandsEnum.values()) {
            commands.put(c.toString(), c);
        }
    }

    /**
     * Adds an alias to replace a current command
     *
     * @param alias  alias to be used
     * @param target command to be simplified
     * @throws DukeException thrown when invalid
     */
    public void addAlias(String alias, String target) throws DukeException {
        if (!commands.containsKey(target)) {
            throw new CommandDoesNotExistException(alias);
        }
        if (commands.containsKey(alias)) {
            throw new CommandAlreadyExistException(alias);
        }

        commands.put(alias, commands.get(target));
    }

    /**
     * Deletes a alias from the alias list
     *
     * @param alias to be deleted
     * @throws DukeException thrown when alias doesn't exist or trying to delete
     *                       base commands
     */
    public void deleteAlias(String alias) throws DukeException {
        if (!commands.containsKey(alias)) {
            throw new CommandDoesNotExistException(alias);
        }

        if (commands.get(alias).toString().equals(alias)) {
            throw new CantDeleteDefaultException(alias);
        }

        commands.remove(alias);
    }

    /**
     * Method to get the correct enum based on string version
     *
     * @return returns the Enum for the command
     */
    public CommandsEnum getCommand(String command) {
        if (commands.containsKey(command)) {
            return commands.get(command);
        }
        return CommandsEnum.INVALID;
    }
}
