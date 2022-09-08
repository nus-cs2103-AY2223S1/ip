package commands;

import exceptions.DukeException;
import input.Input;

import java.util.Arrays;
import java.util.Map;

/**
 * Command to see help message for all or specific command
 */
public class HelpCommand extends Command {
    private Map<String, Command> commandMap;

    /**
     * Creates a Help command
     * @param commandMap Map of all command names to commands, possibly including help command itself
     */
    public HelpCommand(Map<String, Command> commandMap) {
        super("help");
        this.commandMap = commandMap;

    }

    @Override
    public CommandResponse run(Input input) throws DukeException {
        if (input.getNumberOfTokens() <= 1) {
            return new CommandResponse("long help");
        }
        return new CommandResponse("specific help");
    }
}
